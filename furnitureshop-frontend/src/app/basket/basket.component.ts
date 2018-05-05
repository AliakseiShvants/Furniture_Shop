import {Component, OnInit} from '@angular/core';
import {BasketItem} from '../../domain/shop/basket-item';
import {Product} from '../../domain/product/product';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomerService} from '../../service/customer.service';
import {Uiresponse} from '../../domain/uiresponse';
import {AppComponent} from '../app.component';
import {User} from '../../domain/user/user';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})
export class BasketComponent implements OnInit {

  user: User;
  customerId: number;
  basketItems: BasketItem[] = new Array(0);
  isDeleted = false;
  isOrderFormed = false;

  constructor(private router: Router,
              private customerService: CustomerService,
              private app: AppComponent) { }

  ngOnInit() {
    this.user = this.app.user;
    // this.customerId = this.route.snapshot.params['id'];
    this.loadBasketItems(this.user.id);
  }

  isEmpty(){
    return this.basketItems.length === 0;
  }

  private loadBasketItems(id: number) {
    if(id > 0){
      this.customerService.getAllBasketItems(this.customerId).subscribe(
        (res: Uiresponse) => {
          this.basketItems = res.body;
        }
      )
    } else {
      this.app.guestBasket
        .forEach(item => this.basketItems.push(new BasketItem(item.product, 1, item.price)));
    }
  }

  delete(basketItem: BasketItem) {
    if(this.user.id > 0){
      this.basketItems = this.basketItems.filter(item => item.product.id !== basketItem.product.id);
      this.customerService.deleteBasketItem(this.customerId, basketItem.product.id).subscribe(
        (res: Uiresponse) => {
          this.isDeleted = res.success;
        }
      );
    } else {
      this.app.guestBasket.filter(item => item.product.id !== basketItem.product.id);
    }
  }

  totalPrice() {
    let sum = 0;
    for (const item of this.basketItems) {
      sum = sum + item.price * item.quantity;
    }
    return sum;
  }

  makeOrder(){
    if (this.user.id === 0){
      this.router.navigate(['/register']);
    } else if (this.user.requisite === null){
      this.router.navigate(['/profile', 'addRequisite']);
    } else {
      this.customerService.makeOrder(this.customerId, this.basketItems)
        .subscribe(
          (res: Uiresponse) => {
            this.isOrderFormed = res.success;
          }
        );
    }
  }
}
