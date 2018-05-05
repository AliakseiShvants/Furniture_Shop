import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {AuthorizationData} from '../../domain/user/authorization-data';
import {Uiresponse} from '../../domain/uiresponse';
import {AuthorizationService} from '../../service/authorization.service';
import {AppComponent} from '../app.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  fullName: string;
  login: string;
  password: string;
  email: string;
  confirmPassword: string;

  isShortLogin = false;
  isShortPass = false;
  notEqual = false;
  isShortName = false;
  isIncorrectEmail = false;
  isRegistered = false;
  isExist = false;

  modalRef: BsModalRef;

  @ViewChild('registerModal')
  private registerTemplate: TemplateRef<any>;

  constructor(private modalService: BsModalService,
              private authService: AuthorizationService,
              private app: AppComponent,
              private router: Router,) { }

  ngOnInit() {
    this.openRegister();
  }

  openRegister() {
    this.isRegistered = false;
    this.isExist = false;
    this.modalRef = this.modalService.show(this.registerTemplate);
  }

  submitRegister() {
    if(this.validateRegister(this.fullName, this.email, this.login, this.password, this.confirmPassword)){
      this.authService.register(new AuthorizationData(this.fullName, this.login, this.password, this.email))
        .subscribe(
          (data: Uiresponse) => {
            this.isRegistered = data.success;
            this.isExist = !data.success;
            this.app.user = data.body;
            this.modalRef.hide();
            this.router.navigate(['/login']);
          }
        );
    }
  }

  validateRegister(_name: string, _email: string, _login: string, _pass: string, _confirm: string): boolean {
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
    if (_pass.length < 4) {
      this.isShortPass = true;
      flag = false;
    }
    if (_pass !== _confirm) {
      this.notEqual = true;
      flag = false;
    }
    return flag;
  }

  clean(){
    this.isExist = this.isIncorrectEmail = this.isShortPass = this.isShortLogin = this.isShortName = this.notEqual = this.isExist = false;
  }
}
