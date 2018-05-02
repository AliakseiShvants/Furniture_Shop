import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthorizationData} from '../domain/user/authorization-data';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthorizationService {

  private LOGIN = 'api/login';
  private REGISTER = 'api/register';
  private LOGOUT = 'api/logout';

  constructor(private http: HttpClient) { }

  register(data: AuthorizationData) {
    return this.http.post(this.REGISTER, data);
  }

  login(data: AuthorizationData) {

    // let requestHeaders = new HttpHeaders({
    //   'Accept': 'application/json',
    //   'Content-Type': 'application/x-www-form-urlencoded'
    // });

    return this.http.post(this.LOGIN, data);
  }

  logout() {
    // this.http.get(this.LOGOUT);
  }
}
