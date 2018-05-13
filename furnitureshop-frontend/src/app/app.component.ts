import {ChangeDetectorRef, Component, OnInit, Output, SimpleChanges} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {User} from '../domain/user/user';
import {Role} from '../domain/user/role';
import {Storage} from '../domain/shop/storage';
import {Uiresponse} from '../domain/uiresponse';
import {CustomerService} from '../service/customer.service';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';
import {Basket} from '../domain/shop/basket';
import {StorageService} from '../service/storage.service';
import {GoodPriceComponent} from './good-price/good-price.component';
import {UtilService} from '../service/util.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  COURSE = 2.4;
  RU = 'ru';
  EN = 'en';
  user = new User(0);
  basketList: Basket[] = new Array(0);
  guestBasketList: Basket[] = new Array(0);
  lang: string;

  constructor(private customerService: CustomerService,
              private utilService: UtilService,
              private cd: ChangeDetectorRef,
              private translate: TranslateService) {

    translate.currentLang = this.RU;
    this.lang = this.RU;
  }

  /**
   * A method that enables language
   * @param {string} lang a language code
   */
  switchLang(lang: string) {
    this.translate.use(lang);
    this.utilService.onLangChanged.emit(lang);
  }

  ngOnInit() {
    this.translate.setDefaultLang(this.RU);
    this.user.role = new Role(0, 'ROLE_GUEST');
    this.loadUserBasket(this.user.id);
  }

  /**
   * A method that gets a basket of {@link User} by his id
   * @param {number} id field of {@link User} entity
   */
  loadUserBasket(id: number){
    if (!this.isEmpty(this.guestBasketList)){
      this.guestBasketList.forEach(item => this.customerService.addProductToBasket(id, item.product.id).subscribe(
        () => this.getBasketByUserId(id)
      ));
      this.guestBasketList = new Array(0);
    } else {
      this.getBasketByUserId(id);
    }
  }

  private getBasketByUserId(id: number){
    this.customerService.getBasketList(id).subscribe(
      (res: Uiresponse) => {
        this.basketList = res.body;
      }
    );
  }

  isEmpty(list: any[]) {
    return list.length === 0;
  }
}

