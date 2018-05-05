import { Component, OnInit } from '@angular/core';
import {Storage} from '../../domain/shop/storage';
import {AppComponent} from '../app.component';
import {UtilService} from '../../service/util.service';
import {Uiresponse} from '../../domain/uiresponse';
import {CustomerService} from '../../service/customer.service';
import {StorageService} from '../../service/storage.service';

@Component({
  selector: 'app-good-price',
  templateUrl: './good-price.component.html',
  styleUrls: ['./good-price.component.css']
})
export class GoodPriceComponent implements OnInit {

  cheapStorageList: Storage[];
  addedProductId: number;

  constructor(private app: AppComponent,
              private storageService: StorageService,
              private customerService: CustomerService) { }

  ngOnInit() {
    this.getCheapProductList();
  }

  private getCheapProductList() {
    this.storageService.getCheapProductList()
      .subscribe(
        (res: Uiresponse) => {
          this.cheapStorageList = res.body;
        }
      )
  }

  addToBasket(storageItem: Storage){
    if (this.app.user.id !== 0){
      this.customerService.addProductToBasket(this.app.user.id, storageItem.product.id)
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

  disabled(item: Storage){
    if(!this.available(item)){
      return 'disabled';
    } else {
      return '';
    }
  }

  private available(item: Storage){
    return item.quantity > 0;
  }
}
