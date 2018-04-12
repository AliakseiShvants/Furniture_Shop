import { Component, OnInit } from '@angular/core';
import {User} from '../../domain/user/user';
import {Role} from '../../domain/user/role';
import {Requisite} from '../../domain/shop/requisite';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  fullName: string;
  birthday: string;
  sex: boolean;

  user: User;
  requisite: Requisite;

  showForm = 'profile';

  constructor() { }

  ngOnInit() {
    this.mock();
  }

  private mock() {
    this.user = new User('test', 'test_log', 'test_pass', 'test_email',
      '2000-01-01', true, Role.USER);
    this.requisite = new Requisite('20000', 'RB', 'Grodno', 'street 1-12');
  }

  switchForm(form: string){
    this.showForm = form;
  }

}
