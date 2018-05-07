import {ChangeDetectorRef, Component, OnInit, SimpleChanges} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {User} from '../domain/user/user';
import {Role} from '../domain/user/role';
import {Storage} from '../domain/shop/storage';
import {Uiresponse} from '../domain/uiresponse';
import {CustomerService} from '../service/customer.service';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';
import {Basket} from '../domain/shop/basket';

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

  private subscription: Subscription;

  constructor(private customerService: CustomerService,
              private cd: ChangeDetectorRef,
              private translate: TranslateService) {

    translate.setDefaultLang(this.EN);
  }

  /**
   * A method that enables language
   * @param {string} lang a language code
   */
  switchLang(lang: string) {
    this.translate.use(lang);
  }

  ngOnInit() {
    this.user.role = new Role(0, 'ROLE_GUEST');
    // this.loadUserBasket(this.user.id);
  }

  /**
   * A method that gets a basket of {@link User} by his id
   * @param {number} id field of {@link User} entity
   */
  loadUserBasket(id: number){
    if (!this.isEmpty(this.guestBasketList)){
      this.guestBasketList.forEach(item => this.customerService.addProductToBasket(id, item.product.id).subscribe());
      this.guestBasketList = new Array(0);
    }
    this.getBasketByUserId(id);
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

