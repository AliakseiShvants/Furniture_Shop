import {Component, OnInit} from '@angular/core';
import {BasketItem} from '../../domain/shop/basket-item';
import {Product} from '../../domain/product/product';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomerService} from '../../service/customer.service';
import {Uiresponse} from '../../domain/uiresponse';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})
export class BasketComponent implements OnInit {

  customerId: number;
  basketItems: BasketItem[];
  sum = 0;
  isDeleted = false;

  constructor(private route: ActivatedRoute,
              private customerService: CustomerService) { }

  ngOnInit() {
    this.customerId = this.route.snapshot.params['id'];
    this.loadBasketItems();
  }

  private loadBasketItems() {
    this.customerService.getAllBasketItems(this.customerId).subscribe(
      (res: Uiresponse) => {
        this.basketItems = res.body;
      }
    )
  }

  delete(productId: number) {
    this.basketItems = this.basketItems.filter(item => item.product.id !== productId);

    this.customerService.deleteBasketItem(this.customerId, productId).subscribe(
      (res: Uiresponse) => {
        this.isDeleted = res.success;
        console.log(this.isDeleted);
      }
    );
  }

  totalPrice() {
    this.sum = 0;
    for (const item of this.basketItems) {
      this.sum = this.sum + item.price * item.quantity;
    }
    return this.sum;
  }

  makeOrder(){

  }
}
