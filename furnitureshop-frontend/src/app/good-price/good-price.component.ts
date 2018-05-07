import { Component, OnInit } from '@angular/core';
import {Storage} from '../../domain/shop/storage';
import {AppComponent} from '../app.component';
import {UtilService} from '../../service/util.service';
import {Uiresponse} from '../../domain/uiresponse';
import {CustomerService} from '../../service/customer.service';
import {StorageService} from '../../service/storage.service';
import {TranslateService} from '@ngx-translate/core';
import {Basket} from '../../domain/shop/basket';

@Component({
  selector: 'app-good-price',
  templateUrl: './good-price.component.html',
  styleUrls: ['./good-price.component.css']
})
export class GoodPriceComponent implements OnInit {

  cheapStorageList: Storage[];
  addedProductId: number;

  constructor(private app: AppComponent,
              private translate: TranslateService,
              private storageService: StorageService,
              private customerService: CustomerService) { }

  ngOnInit() {
    this.getCheapProductList();
  }

  /**
   * Gets a {@link Product} list with four the cheapest {@link Product} items of each {@link Category}
   */
  private getCheapProductList() {
    this.storageService.getCheapProductList()
      .subscribe(
        (res: Uiresponse) => {
          this.cheapStorageList = res.body;
        }
      )
  }

  /**
   * A method that adds a {@link Product} item to the basket of registered user
   * or adds the {@link Product} in the guest basket.
   * @param {Storage} storageItem
   */
  addToBasket(storageItem: Storage){
    if (this.app.user.id > 0){
      this.customerService.addProductToBasket(this.app.user.id, storageItem.product.id)
        .subscribe(
          (res: Uiresponse) => {
            this.addedProductId = storageItem.product.id;
          }
        );
      this.app.basketList.push(new Basket(storageItem.product, 1, storageItem.price));
    } else {
      this.app.guestBasketList.push(new Basket(storageItem.product, 1, storageItem.price));
    }
  }

  /**
   * Checks if a product in the basket
   * @param {Storage} storageItem
   * @returns {boolean} true if a product in the basket and false otherwise
   */
  isAdded(storageItem: Storage) {
    if (this.app.user.id > 0){
      return this.app.basketList.some(item => item.product.id === storageItem.product.id);
    } else {
      return this.app.guestBasketList.some(item => item.product.id === storageItem.product.id);
    }
  }

  /**
   * A method that disables a card with product if it already in the basket.
   * @param {Storage} item
   * @returns {string}
   */
  disabled(item: Storage){
    if(!this.available(item)){
      return 'disabled';
    } else {
      return '';
    }
  }

  /**
   * A method that checks if a {@link Product} item in the {@link User} basket.
   * @param {Storage} item a {@link Storage} entity
   * @returns {boolean} true if a {@link Product} item is not in the basket
   */
  private available(item: Storage){
    return item.quantity > 0;
  }

  /**
   * A method that returns a price value according current currency
   * @param {number} price
   * @returns {number | string}
   */
  getPrice(price: number){
    return this.translate.currentLang === this.app.RU ? price : (price / this.app.COURSE).toFixed(2);
  }
}
