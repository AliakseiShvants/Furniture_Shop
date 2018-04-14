import {Component, OnInit} from '@angular/core';
import {BasketItem} from '../../domain/shop/basket-item';
import {Product} from '../../domain/product/product';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})
export class BasketComponent implements OnInit {
  mockBasketItems = [
    new BasketItem(1, new Product('CDEq', 'Spring', 51.00), 5),
    new BasketItem(2, new Product('CDEa', 'SpringA', 45.00), 6),
    new BasketItem(3, new Product('CDEz', 'Spring1', 56.00), 7),
    new BasketItem(4, new Product('CDEza', 'Spring11', 65.00), 8),
  ];

  basket = this.mockBasketItems;
  sum = 0;
  constructor() { }

  ngOnInit() {
  }

  delete(item: BasketItem) {
    this.basket = this.basket.filter(itm => itm !== item);
    this.totalPrice();
  }

  totalPrice() {
    this.sum = 0;
    for (const item of this.basket) {
      this.sum = this.sum + item.product.price;
    }
    return this.sum;
  }
}
