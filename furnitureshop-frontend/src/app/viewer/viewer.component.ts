import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnChanges, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
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
  changeDetection: ChangeDetectionStrategy.Default
})
export class ViewerComponent implements  OnInit  {

  user: User;
  addedProductId: number;

  storageItems: Storage[];
  categoryTitle: string;
  category: Category;

  isAdded = false;

  private subscription: Subscription;

  constructor(private route: ActivatedRoute,
              private productService: ProductService,
              private customerService: CustomerService,
              private cd: ChangeDetectorRef) {

    this.subscription = this.route.params
      .subscribe(
        params => this.categoryTitle = params['category']
      );
    // this.getStorageItems();

    setInterval(() => {
      this.getStorageItems();
      // this.cd.detectChanges();
    }, 1000);
  }

  ngOnInit() {
    this.user = this.customerService.getUser();
    // this.categoryTitle = this.route.snapshot.params['category'];
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

}
