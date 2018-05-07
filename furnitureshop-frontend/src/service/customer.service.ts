import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {AuthorizationData} from '../domain/user/authorization-data';
import {User} from '../domain/user/user';
import {Requisite} from '../domain/shop/requisite';
import {Basket} from '../domain/shop/basket';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class CustomerService {

  private _user = new User();

  private base = 'api/customer/';
  private requisite = '/requisite';
  private basket = '/basket/';
  private order = '/order/';

  private delete = '/delete';
  private update = '/update';
  private add = '/add';
  private all = 'all';
  private book = 'book';


  constructor(private httpClient: HttpClient) { }


  getUser(): User {
    return this._user;
  }

  setUser(value: User) {
    this._user = value;
  }

  updateProfile(user: User) {
    return this.httpClient.patch(this.base + this.update, user);
  }

  getRequisite(customerId: number) {
    return this.httpClient.get(this.base + customerId + this.requisite);
  }

  addRequisite(customerId: number, requisite: Requisite) {
    return this.httpClient.post(this.base + customerId + this.requisite + this.add, requisite);
  }

  updateRequisite(customerId: number, requisite: Requisite) {
    return this.httpClient.patch(this.base + customerId + this.requisite + this.update, requisite);
  }

  deleteCustomer(customerId: number) {
    return this.httpClient.delete(this.base + customerId + this.delete);
  }

  /**
   * A method that adds {@link Product} entity to {@link User}'s basket.
   * @param {number} customerId id field of {@link User}
   * @param {number} productId id field of {@link Product}
   * @returns {Observable<Object>} new {@link Product} entity in the basket
   */
  addProductToBasket(customerId: number, productId: number){
    return this.httpClient.get(this.base + customerId + this.basket + productId + this.add);
  }

  getBasketList(customerId: number){
    return this.httpClient.get(this.base + customerId + this.basket + this.all);
  }

  deleteBasketItem(customerId: number, productId: number) {
    return this.httpClient.delete(this.base + customerId + this.basket + productId + this.delete);
  }

  getAllCustomerOrders(customerId: number) {
    return this.httpClient.get(this.base + customerId + this.order + this.all);
  }

  getOrderInfo(customerId: number, orderId: number){
    return this.httpClient.get(this.base + customerId + this.order + orderId);
  }

  makeOrder(customerId: number, basketItems: Basket[]){
    return this.httpClient.post(this.base + customerId + this.basket + this.book, basketItems);
  }
}
