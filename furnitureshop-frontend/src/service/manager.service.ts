import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Order} from '../domain/shop/order';

@Injectable()
export class ManagerService {

  private manager = 'api/manager/';
  private order = '/order/';
  private product = '/product/';
  private all = 'all';
  private delet = '/delete';
  private update = '/update';
  private storage = '/storage';

  constructor(private httpClient: HttpClient) { }

  getAllManagerOrders(userId: number) {
    return this.httpClient.get(this.manager + userId + this.order + this.all);
  }

  deleteOrder(managerId: number, orderId: number) {
    return this.httpClient.delete(this.manager + managerId + this.order + orderId + this.delet);
  }

  updateOrder(userId: number, order: Order) {
    return this.httpClient.patch(this.manager + userId + this.order + order.id + this.update, order);
  }

  getManagerProducts(userId: number) {
    return this.httpClient.get(this.manager + userId + this.product + this.all);
  }

  getManagerStorage(userId: number) {
    return this.httpClient.get(this.manager + userId + this.storage);
  }
}
