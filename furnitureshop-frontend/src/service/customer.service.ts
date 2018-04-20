import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {AuthorizationData} from '../domain/user/authorization-data';
import {User} from '../domain/user/user';
import {Requisite} from '../domain/shop/requisite';

@Injectable()
export class CustomerService {

  private _user = new User();

  private addCustomer = 'api/admin/addCustomer';
  private customers = 'api/admin/customers';
  private customer = 'api/customer/';
  private delete = '/delete';

  private requisite = '/requisite/';
  private update = '/update';
  private _addRequisite = '/addRequisite';

  constructor(private httpClient: HttpClient) { }


  getUser(): User {
    return this._user;
  }

  setUser(value: User) {
    this._user = value;
  }

  addNewCustomer(data: AuthorizationData) {
    return this.httpClient.post(this.addCustomer, data);
  }

  getAllCustomers() {
    return this.httpClient.get(this.customers);
  }

  updateProfile(user: User) {
    return this.httpClient.patch(this.customer + this.update, user);
  }

  getRequisite(customerId: number) {
    return this.httpClient.get(this.customer + customerId + this.requisite);
  }

  addRequisite(customerId: number, requisite: Requisite) {
    return this.httpClient.post(this.customer + customerId + this._addRequisite, requisite);
  }

  updateRequisite(customerId: number, requisite: Requisite) {
    return this.httpClient.post(this.customer + customerId + this.requisite + requisite.id + this.update, requisite);
  }

  deleteCustomer(customerId: number) {
    return this.httpClient.delete(this.customer + customerId + this.delete);
  }
}
