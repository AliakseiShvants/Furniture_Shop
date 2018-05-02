import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Order} from '../domain/shop/order';
import {Product} from '../domain/product/product';

@Injectable()
export class ManagerService {

  private manager = 'api/manager/';
  private order = '/order/';
  private product = '/product/';
  private storage = '/storage';

  private all = 'all';
  private add = 'add';
  private delet = '/delete';
  private update = 'update';


  constructor(private httpClient: HttpClient) { }

  getAllManagerOrders(userId: number) {
    return this.httpClient.get(this.manager + userId + this.order + this.all);
  }

  deleteOrder(managerId: number, orderId: number) {
    return this.httpClient.delete(this.manager + managerId + this.order + orderId + this.delet);
  }

  updateOrder(userId: number, order: Order) {
    return this.httpClient.patch(this.manager + userId + this.order + this.update, order);
  }

  getManagerProducts(userId: number) {
    return this.httpClient.get(this.manager + userId + this.product + this.all);
  }

  getManagerStorage(userId: number) {
    return this.httpClient.get(this.manager + userId + this.storage);
  }

  addProduct(userId: number, product: Product) {
    return this.httpClient.post(this.manager + userId + this.product + this.add, product);
  }

  updateProduct(userId: number, item: Product) {
    return this.httpClient.post(this.manager + userId + this.product + this.update, item);
  }

  deleteProduct(userId: number, productId: number) {
    return this.httpClient.delete(this.manager + userId + this.product + productId + this.delet);
  }


}
