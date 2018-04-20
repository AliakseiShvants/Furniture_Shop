import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ProductService} from '../../service/product.service';
import {StorageItem} from '../../domain/shop/storage-item';
import {Uiresponse} from '../../domain/uiresponse';
import {User} from '../../domain/user/user';
import {CustomerService} from '../../service/customer.service';

@Component({
  selector: 'app-viewer',
  templateUrl: './viewer.component.html',
  styleUrls: ['./viewer.component.css']
})
export class ViewerComponent implements OnInit {

  user: User;
  addedProductId: number;
  storageItems: StorageItem[];
  category: string;

  isAdded = false;

  constructor(private route: ActivatedRoute,
              private productService: ProductService,
              private customerService: CustomerService) { }

  ngOnInit() {
    this.user = this.customerService.getUser();
    this.category = this.route.snapshot.params['category'];
    this.getStorageItems();

  }

  private getStorageItems() {
    this.productService.getStorageItemsByProductCategory(this.category)
      .subscribe(
        (data: Uiresponse) => {
          if (!data.success){
            alert('no products');
          } else {
            this.storageItems = data.body;
          }
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
