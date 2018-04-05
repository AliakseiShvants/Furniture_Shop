import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {User} from '../entity/user';
import {Role} from '../entity/role';
import * as $ from 'jquery';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  private fullName: string;
  private login: string;
  private email: string;
  private password: string;
  private confirmPassword: string;

  private success = false;
  private isExist = false;

  private isShortName = false;
  private isIncorrectEmail = false;
  private isShortLogin = false;
  private isShortPass = false;
  private notEqual = false;

  private modalRef: BsModalRef;

  @ViewChild('registerModal')
  private template: TemplateRef<any>;

  constructor(private modalService: BsModalService) {
  }

  ngOnInit() {
    this.openModal(this.template);
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  submit() {
    const newUser = new User(this.fullName, this.email, this.login, this.password, Role.USER);
    this.validateInput(this.fullName, this.email, this.login, this.password, this.confirmPassword);
  }

  validateInput(_name: string, _email: string, _login: string, _pass: string, _confirm: string): boolean {
    this.isShortName = this.isShortLogin = this.isShortPass = this.isIncorrectEmail = this.notEqual = false;
    let flag = true;
    if (_name.length < 4) {
      this.isShortName = true;
      flag = false;
    }
    if (!_email.includes('@')) {
      this.isIncorrectEmail = true;
      flag = false;
    }
    if (_login.length < 4) {
      this.isShortLogin = true;
      flag = false;
    }
    if (_pass.length < 7) {
      this.isShortPass = true;
      flag = false;
    }
    if (_pass !== _confirm) {
      this.notEqual = true;
      flag = false;
    }
    return flag;
  }
}
