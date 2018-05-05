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

@Component({
  selector: 'app-viewer',
  templateUrl: './viewer.component.html',
  styleUrls: ['./viewer.component.css'],
})
export class ViewerComponent implements  OnInit {

  user = new User();
  addedProductId: number;

  storageList: Storage[];
  categoryTitle: string;
  category: Category;

  private subscription: Subscription;
  private CUSTOMER = 'ROLE_USER';

  constructor(private route: ActivatedRoute,
              private router: Router,
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
    this.user = this.app.user;
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

  addToBasket(storageItem: Storage){
    if (this.user.id !== 0){
      this.customerService.addProductToBasket(this.user.id, storageItem.product.id)
        .subscribe(
          (res: Uiresponse) => {
            this.addedProductId = storageItem.product.id;
          }
        );
    } else {
        this.app.guestBasket.push(storageItem);
    }
  }

  isAdded(storageItem: Storage) {
    return this.app.guestBasket.some(item => item.product.id === storageItem.product.id);
  }

  isCustomer(): boolean {
    return this.user.role.title === this.CUSTOMER;
  }

  private available(item: Storage){
    return item.quantity > 0;
  }

  disabled(item: Storage){
    if(!this.available(item)){
      return 'disabled';
    } else {
      return '';
    }
  }
}
