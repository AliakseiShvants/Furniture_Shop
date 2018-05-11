import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnChanges, OnInit} from '@angular/core';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';
import {ProductService} from '../../service/product.service';
import {Storage} from '../../domain/shop/storage';
import {Uiresponse} from '../../domain/uiresponse';
import {User} from '../../domain/user/user';
import {CustomerService} from '../../service/customer.service';
import {Category} from '../../domain/product/category';
import {Subscription} from 'rxjs/Subscription';
import {AppComponent} from '../app.component';
import {StorageService} from '../../service/storage.service';
import {TranslateService} from '@ngx-translate/core';
import {Basket} from '../../domain/shop/basket';

@Component({
  selector: 'app-viewer',
  templateUrl: './viewer.component.html',
  styleUrls: ['./viewer.component.css'],
})
export class ViewerComponent implements  OnInit {

  addedProductId: number;

  storageList: Storage[];
  categoryTitle: string;
  category: Category;

  private subscription: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private translate: TranslateService,
              private storageService: StorageService,
              private customerService: CustomerService,
              private app: AppComponent) {

    this.subscription = this.route.params
      .subscribe(
        params => this.categoryTitle = params['category']
      );

    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };

    router.events.subscribe(
      (evt) => {
        if (evt instanceof NavigationEnd){
          this.router.navigated = false;
        }
      }
    )
  }

  ngOnInit() {
    this.getStorageList();
  }

  private getStorageList() {
    this.storageService.getStorageListByProductCategory(this.categoryTitle)
      .subscribe(
        (res: Uiresponse) => {
          this.storageList = res.body;
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

  private available(item: Storage){
    return item.quantity > 0;
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
