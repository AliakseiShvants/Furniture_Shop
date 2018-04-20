import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Uiresponse} from '../domain/uiresponse';
import {Observable} from 'rxjs/Observable';
import {AuthorizationData} from '../domain/user/authorization-data';

@Injectable()
export class AdminService {

  private base = 'api/admin/';
  private customers = 'customers';
  private addCustomer = 'addCustomer';

  constructor(private http: HttpClient) { }



  addNewCustomer(data: AuthorizationData) {
    return this.http.post(this.base + this.addCustomer, data);
  }
}
