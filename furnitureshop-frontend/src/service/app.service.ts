import { Injectable } from '@angular/core';
import {User} from '../domain/user/user';
import {Requisite} from '../domain/shop/requisite';
import {HttpClient} from '@angular/common/http';
import {Uiresponse} from '../domain/uiresponse';

@Injectable()
export class AppService {

  public appUser: User;
  public userRequisite: Requisite;

  constructor(private http: HttpClient) {}

  initUser(user: User) {
    this.appUser = user;
  }

  initUserRequisite(userId: number) {
     return this.http.get('api/' + userId + '/requisite');

  }
}
