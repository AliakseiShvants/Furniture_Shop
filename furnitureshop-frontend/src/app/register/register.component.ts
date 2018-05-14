import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {AuthorizationData} from '../../domain/user/authorization-data';
import {Uiresponse} from '../../domain/uiresponse';
import {AuthorizationService} from '../../service/authorization.service';
import {AppComponent} from '../app.component';
import {Router} from '@angular/router';
import {Basket} from '../../domain/shop/basket';
import {CustomerService} from '../../service/customer.service';

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
              private customerService: CustomerService,
              private router: Router) { }

  ngOnInit() {
    this.openRegister();
  }

  /**
   * A method that opens a register modal window
   */
  openRegister() {
    this.isRegistered = false;
    this.isExist = false;
    this.modalRef = this.modalService.show(this.registerTemplate);
  }

  /**
   * A method that validates registration input data, register new {@link User} entity and adds selected  {@link Product} items to the
   * user's basket.
   */
  submitRegister() {
    if(this.validateRegister(this.fullName, this.email, this.login, this.password, this.confirmPassword)){

      this.authService.register(new AuthorizationData(this.fullName, this.login, this.password))
        .subscribe(
          (data: Uiresponse) => {
            this.isRegistered = data.success;
            this.isExist = !data.success;

            if (this.isRegistered){
              // this.app.user = data.body;
              setTimeout(() => {
                this.modalRef.hide();
                this.router.navigate(['/login']);
              }, 3000);
            }
          }
        );
    }
  }

  /**
   * A method that validates register fields
   * @param {string} _name
   * @param {string} _email
   * @param {string} _login
   * @param {string} _pass
   * @param {string} _confirm
   * @returns {boolean}
   */
  validateRegister(_name: string, _email: string, _login: string, _pass: string, _confirm: string): boolean {
    this.isShortName = this.isShortLogin = this.isShortPass = this.isIncorrectEmail = this.notEqual = false;
    if (_name.length < 4) {
      this.isShortName = true;
    }
    if (!_email.includes('@')) {
      this.isIncorrectEmail = true;
    }
    if (_login.length < 4) {
      this.isShortLogin = true;
    }
    if (_pass.length < 4) {
      this.isShortPass = true;
    }
    if (_pass !== _confirm) {
      this.notEqual = true;
    }
    return !(this.isShortName || this.isShortLogin || this.isShortPass || this.isIncorrectEmail || this.notEqual);
  }

  /**
   * A method that triggers all boolean fields in false
   */
  clean(){
    this.isExist = this.isIncorrectEmail = this.isShortPass = this.isShortLogin = this.isShortName = this.notEqual = this.isExist = false;
  }
}
