import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthorizationData} from '../domain/user/authorization-data';
import {CustomerService} from './customer.service';
import {Uiresponse} from '../domain/uiresponse';
import {User} from '../domain/user/user';

@Injectable()
export class AuthorizationService {

  private loginUrl = 'api/login';
  private registerUrl = 'api/register';

  constructor(private http: HttpClient) { }

  register(data: AuthorizationData) {
    return this.http.post(this.registerUrl, data);
  }

  login(data: AuthorizationData) {
    return this.http.post(this.loginUrl, data);
  }
}
