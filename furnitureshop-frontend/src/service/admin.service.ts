import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Uiresponse} from '../domain/uiresponse';
import {Observable} from 'rxjs/Observable';
import {AuthorizationData} from '../domain/user/authorization-data';
import {User} from '../domain/user/user';
import {Requisite} from '../domain/shop/requisite';

@Injectable()
export class AdminService {

  private base = 'api/admin/';
  private customer = 'customer/';
  private user = 'user/';
  private manager = 'manager/';
  private product = 'product/';

  private add = 'add';
  private all = 'all';
  private update = 'update';
  private delet = 'delet';

  private roles = 'roles';

  constructor(private http: HttpClient) { }

  addNewCustomer(data: AuthorizationData) {
    return this.http.post(this.base + this.customer + this.add, data);
  }

  getAllCustomers() {
    return this.http.get(this.base + this.customer + this.all);
  }

  updateUser(user: User) {
    return this.http.patch(this.base + this.customer + this.update, user);
  }

  deleteCustomer(customerId: number) {
    return this.http.delete(this.base + this.customer + this.delet);
  }

  updateRequisite(requisite: Requisite) {
    return this.http.patch(this.base + this.product + this.update, requisite);
  }

  getAllManagers() {
    return this.http.get(this.base + this.manager + this.all);
  }

  addNewManager(data: AuthorizationData) {
    return this.http.post(this.base + this.manager + this.add, data);
  }

}
