import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Customer} from '../domain/customer/customer';

@Injectable()
export class CustomerService {

  private loginUrl = 'api/customer/login';

  constructor(private httpClient: HttpClient) { }

  login(log: string, pass: string) {
    const body = [log, pass];
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const options = { headers: headers };

    return this.httpClient.post(this.loginUrl, body, options);
  }

  mockLogin(login: string, password: string) {
    return this.httpClient.post('api/customer/mockLogin', [login, password]);
  }


}
