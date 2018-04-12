import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {User} from '../../domain/user/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  fullName: string;
  login: string;
  email: string;
  password: string;
  confirmPassword: string;

  success = false;
  isExist = false;

  isShortName = false;
  isIncorrectEmail = false;
  isShortLogin = false;
  isShortPass = false;
  notEqual = false;

  modalRef: BsModalRef;

  @ViewChild('registerModal')
  template: TemplateRef<any>;

  constructor(private modalService: BsModalService) {
  }

  ngOnInit() {
    this.openModal(this.template);
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  submit() {
    const newUser = new User(this.fullName, this.email, this.login, this.password);
    this.validateInput(this.fullName, this.email, this.login, this.password, this.confirmPassword);

  }

  validateInput(_name: string, _email: string, _login: string, _pass: string, _confirm: string): boolean {
    this.isShortName = this.isShortLogin = this.isShortPass = this.isIncorrectEmail = this.notEqual = false;
    let flag = true;
    if (_name.length > 3) {
      this.isShortName = true;
      flag = false;
    }
    if (!_email.includes('@')) {
      this.isIncorrectEmail = true;
      flag = false;
    }
    if (_login.length > 3) {
      this.isShortLogin = true;
      flag = false;
    }
    if (_pass.length > 3) {
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
