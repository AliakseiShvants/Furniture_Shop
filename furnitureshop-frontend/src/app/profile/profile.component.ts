import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {User} from '../../domain/user/user';
import {Requisite} from '../../domain/shop/requisite';
import {Uiresponse} from '../../domain/uiresponse';
import {CustomerService} from '../../service/customer.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AppComponent} from '../app.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User;

  zip:string;
  country:string;
  city:string;
  address:string;

  isDeleted = false;
  isUpdated = false;
  isAdded = false;

  showForm = 'profile';

  constructor(private customerService: CustomerService,
              private app: AppComponent,
              private router: Router,
              private route: ActivatedRoute,
              private cd: ChangeDetectorRef) {

    setInterval(() => {
      this.user = app.user;
      this.cd.detectChanges();
    }, 1000);
  }

  ngOnInit() {
    this.user = this.app.user;
    this.showForm = this.route.snapshot.params['form'];
  }

  updateProfile() {
    this.customerService.updateProfile(this.user)
      .subscribe(
        (data: Uiresponse) => {
           this.isUpdated = data.success;
          this.customerService.setUser(data.body);
        }
      );
  }

  addRequisite() {
    this.customerService.addRequisite(this.user.id, new Requisite(this.zip, this.country, this.city, this.address))
      .subscribe(
        (data: Uiresponse) => {
          this.isAdded = data.success;
          this.user.requisite = data.body;
          this.customerService.setUser(this.user);
        }
      );
  }

  updateRequisite(){
    this.customerService.updateRequisite(this.user.id, this.user.requisite)
      .subscribe(
        (data: Uiresponse) => {
          this.isUpdated = data.success;
          this.user.requisite = data.body;
          this.customerService.setUser(this.user);
        }
      );
  }

  delete() {
    this.customerService.deleteCustomer(this.user.id)
      .subscribe(
        (data: Uiresponse) => {
          this.isDeleted = data.success;
          this.customerService.setUser(null);
          this.router.navigate(['']);
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
}
