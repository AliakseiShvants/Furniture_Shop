import {ChangeDetectorRef, Component, Input, OnInit} from '@angular/core';
import {User} from '../../domain/user/user';
import {Requisite} from '../../domain/shop/requisite';
import {Uiresponse} from '../../domain/uiresponse';
import {CustomerService} from '../../service/customer.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AppComponent} from '../app.component';
import {DatePipe} from '@angular/common';
import {Locale} from 'ngx-bootstrap/chronos/locale/locale.class';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User;
  dateFormat = 'dd-MM-yyyy';
  countryList_EN = ['Belarus', 'Russia'];
  countryList_RU = ['Беларусь', 'Россия'];
  cityList_EN = ['Hrodna', 'Minsk', 'Moscow', 'St.Petersburg'];
  cityList_RU = ['Гродно', 'Минск', 'Москва', 'С.Петербург'];

  zip:string;
  country:string;
  city:string;
  address:string;

  isDeleted = false;
  isUpdated = false;
  isAdded = false;
  isZip = false;
  isAddress = false;
  isShortName = false;
  isNotEmail = false;

  showForm = 'profile';

  constructor(private customerService: CustomerService,
              private app: AppComponent,
              private router: Router,
              private route: ActivatedRoute,
              private translate: TranslateService,
              private datePipe: DatePipe) {
  }

  ngOnInit() {
    this.user = this.app.user;
    this.showForm = this.route.snapshot.params['form'];
  }

  getBirthday(birthday: Date) {
    return this.datePipe.transform(birthday, this.dateFormat);
  }

  updateProfile() {
    if (this.validateProfile(this.user)){
      this.customerService.updateProfile(this.user)
        .subscribe(
          (data: Uiresponse) => {
            this.isUpdated = data.success;
            this.app.user = this.user = data.body;
          }
        );
    }
  }

  private validateProfile(user: User) {
    this.isShortName = this.isNotEmail = this.isUpdated = false;
    if (user.fullName.length < 4){
      this.isShortName = true;
    }
    if (!user.email.includes('@') && user.email.length < 7){
      this.isNotEmail = true;
    }
    return !(this.isShortName || this.isNotEmail);
  }

  addRequisite() {
    let requisite = new Requisite(this.zip, this.country, this.city, this.address);
    if (this.validateRequisite(requisite)){
      this.customerService.addRequisite(this.user.id, requisite)
        .subscribe(
          (data: Uiresponse) => {
            this.isAdded = data.success;
            this.app.user.requisite = this.user.requisite = data.body;
          }
        );
    }
  }

  updateRequisite(){
    if (this.validateRequisite(this.user.requisite)){
      this.customerService.updateRequisite(this.user.id, this.user.requisite)
        .subscribe(
          (data: Uiresponse) => {
            this.isUpdated = data.success;
            this.app.user.requisite = this.user.requisite = data.body;
          }
        );
    }
  }

  delete() {
    this.customerService.deleteCustomer(this.user.id)
      .subscribe(
        (data: Uiresponse) => {
          this.isDeleted = data.success;
          this.app.user = this.user = new User(0);
          this.router.navigate(['/']);
        }
      );
  }

  switchForm(form: string) {
    this.isUpdated = false;
    this.isAdded = false;
    this.showForm = form;
  }

  getSex(user: User){
    if (user.sex){
      return 'checked';
    }
    return '';
  }

  requisiteExists(){
    return this.user.requisite != null;
  }

  selected(first: any, second: any){
    if (first === second){
      return 'selected';
    }
  }

  getCountryList(){
    let lang = this.translate.currentLang;
    return lang === this.app.EN ? this.countryList_EN : this.countryList_RU;
  }

  getCityList(){
    let lang = this.translate.currentLang;
    return lang === this.app.EN ? this.cityList_EN : this.cityList_RU;
  }

  validateRequisite(requisite: Requisite){
    this.isZip = this.isAddress = this.isUpdated = this.isAdded = this.isDeleted = false;
    if (requisite.zip.length < 6){
      this.isZip = true;
    }
    if (requisite.address.length < 10){
      this.isAddress = true;
    }
    return !(this.isZip || this.isAddress);
  }
}
