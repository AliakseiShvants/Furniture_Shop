import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnChanges, OnInit} from '@angular/core';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';
import {ProductService} from '../../service/product.service';
import {Storage} from '../../domain/shop/storage';
import {Uiresponse} from '../../domain/uiresponse';
import {User} from '../../domain/user/user';
import {CustomerService} from '../../service/customer.service';
import {Category} from '../../domain/product/category';
import {Subscription} from 'rxjs/Subscription';

@Component({
  selector: 'app-viewer',
  templateUrl: './viewer.component.html',
  styleUrls: ['./viewer.component.css'],
})
export class ViewerComponent implements  OnInit {

  user = new User();
  addedProductId: number;

  storageItems: Storage[];
  categoryTitle: string;
  category: Category;

  isAdded = false;

  private subscription: Subscription;
  private CUSTOMER = 'ROLE_USER';

  constructor(private route: ActivatedRoute,
              private router: Router,
              private productService: ProductService,
              private customerService: CustomerService) {

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
    this.user = this.customerService.getUser();
    this.getStorageItems();
  }

  private getStorageItems() {
    this.productService.getStorageItemsByProductCategory(this.categoryTitle)
      .subscribe(
        (res: Uiresponse) => {
          this.storageItems = res.body;
        }
      )
  }

  addToBasket(productId: number){
    this.customerService.addProductToBasket(this.user.id, productId)
      .subscribe(
        (res: Uiresponse) => {
          this.isAdded = res.success;
          this.addedProductId = productId;
        }
      );
  }

  showSuccessAlert(id: number) {
    return this.isAdded && this.addedProductId === id;
  }

  isCustomer(): boolean {
    return this.user.role === this.CUSTOMER;
  }

}
