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
  isDeleted = false;
  isOrderFormed = false;

  constructor(private route: ActivatedRoute,
              private customerService: CustomerService) { }

  ngOnInit() {
    this.isDeleted = this.isOrderFormed = false;
    this.customerId = this.route.snapshot.params['id'];
    this.loadBasketItems();
  }

  isEmpty(){
    return this.basketItems.length === 0;
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
    let sum = 0;
    for (const item of this.basketItems) {
      sum = sum + item.price * item.quantity;
    }
    return sum;
  }

  makeOrder(){
    this.customerService.makeOrder(this.customerId, this.basketItems)
      .subscribe(
        (res: Uiresponse) => {
            this.isOrderFormed = res.success;
        }
      );
  }
}
