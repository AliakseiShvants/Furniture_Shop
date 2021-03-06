import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Basket} from '../../domain/shop/basket';
import {Product} from '../../domain/product/product';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomerService} from '../../service/customer.service';
import {Uiresponse} from '../../domain/uiresponse';
import {AppComponent} from '../app.component';
import {User} from '../../domain/user/user';
import {TranslateService} from '@ngx-translate/core';
import {Requisite} from '../../domain/shop/requisite';
import {UtilService} from '../../service/util.service';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})
export class BasketComponent implements OnInit {

  basketList: Basket[] = new Array(0);
  isDeleted = false;
  isOrderFormed = false;

  constructor(private router: Router,
              private cd: ChangeDetectorRef,
              private utilService: UtilService,
              private customerService: CustomerService,
              private translate: TranslateService,
              private app: AppComponent) {

    this.utilService.onLangChanged.subscribe(
      (lang: string) => {
        this.customerService.getBasketList(this.app.user.id, lang);
      }
    )
  }

  ngOnInit() {
    this.getBasketList(this.app.user.id, this.translate.currentLang);
  }

  /**
   * Checks if a basket is empty
   * @returns {boolean} true if the basket is empty and false otherwise
   */
  isBasketEmpty(){
    return this.basketList.length === 0;
  }

  /**
   * Gets a {@link User} basket list according registered user or not.
   * @param {number} id field of {@link User} entity
   */
  private getBasketList(id: number, lang: string) {
    if(id > 0){
      this.basketList = this.app.basketList.slice();
    } else {
      this.basketList = this.app.guestBasketList.slice();
    }
  }

  delete(basketItem: Basket) {
    if(this.app.user.id > 0){
      this.app.basketList = this.basketList = this.basketList.filter(item => item.product.id !== basketItem.product.id);
      this.customerService.deleteBasketItem(this.app.user.id, basketItem.product.id).subscribe(
        (res: Uiresponse) => {
          this.isDeleted = res.success;
        }
      );
    } else {
      this.app.guestBasketList = this.basketList = this.basketList.filter(item => item.product.id !== basketItem.product.id);
    }
  }

  /**
   * A method that counts a total price of probable order.
   * @returns {number} money value in BYN currency
   */
  totalPrice() {
    let sum = 0;
    for (const item of this.basketList) {
      sum = sum + this.getPrice(item.price) * item.quantity;
    }
    return sum;
  }

  /**
   * A method that forms an order if user is registered and has a requisite,
   * else if there isn't a requisite then redirect to adding the requisite items,
   * otherwise redirect to 'login' page.
   */
  makeOrder(){
    if (this.app.user.id === 0){
      this.router.navigate(['/login']);
    }
    else if (!this.isRequisiteValid(this.app.user.requisite)){
      this.router.navigate(['/profile', 'addRequisite']);
    }
    else {
      let idList = this.basketList.map(item => item.id);
      this.customerService.makeOrder(this.app.user.id, idList)
        .subscribe(
          (res: Uiresponse) => {
            this.isOrderFormed = res.success;
            this.app.basketList = new Array(0);

            setTimeout(() => {
              this.router.navigate(['']);
            }, 3000);
          }
        );
    }
  }

  private isRequisiteValid(requisite: Requisite) {
    if (requisite !== null){
      return requisite.zip !== null && requisite.country !== null && requisite.city !== null && requisite.address !== null;
    }
    return false;
  }

  /**
   * A method that returns a price value according current currency
   * @param {number} price
   * @returns {number | string}
   */
  getPrice(price: number){
    switch (this.translate.currentLang){
      case this.app.RU: {
        return price;
      }
      case this.app.EN: {
        return (price / this.app.COURSE);
      }
    }
  }
}
