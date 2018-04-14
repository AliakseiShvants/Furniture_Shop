import { Component, OnInit } from '@angular/core';
import {Order} from '../../domain/shop/orderItem';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  mockOrders = [
    new Order(1, 'Бовшевич Анна', 'IN_PROCESSING', '2018/04/13'),
    new Order(2, 'Бовшевич Анна', 'IN_PROCESSING', '2018/04/13'),
    new Order(3, 'Бовшевич Анна', 'ACCEPT', '2018/04/09', '2018/04/12'),
    new Order(4, 'Бовшевич Анна', 'SENT', '2018/04/01', '2018/04/12')
  ];
  counter = 0;
  constructor() { }

  ngOnInit() {
  }

  increment() {
    return ++this.counter;
  }
}
