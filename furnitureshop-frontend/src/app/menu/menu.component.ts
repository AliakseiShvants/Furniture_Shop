import {Component, Input, OnChanges, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {User} from '../../domain/user/user';
import {Role} from '../../domain/user/role';
import {CustomerService} from '../../service/customer.service';
import {ActivatedRoute, NavigationEnd, Params, Router} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {AuthorizationData} from '../../domain/user/authorization-data';
import {Uiresponse} from '../../domain/uiresponse';
import {AuthorizationService} from '../../service/authorization.service';
import {Category} from '../../domain/product/category';
import {ProductService} from '../../service/product.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  user = new User();
  public categoryList: Category[];

  fullName: string;
  login: string;
  password: string;
  email: string;
  confirmPassword: string;

  isLogged = false;
  isRegistered = false;
  isExist = false;
  isNotExist = false;
  isShortLogin = false;
  isShortPass = false;
  isShortName = false;
  isIncorrectEmail = false;
  notEqual = false;


  private CUSTOMER = 'ROLE_USER';
  private ADMIN = 'ROLE_ADMIN';
  private MANAGER = 'ROLE_MANAGER';

  modalRef: BsModalRef;

  @ViewChild('loginModal')
  private loginTemplate: TemplateRef<any>;

  @ViewChild('registerModal')
  private registerTemplate: TemplateRef<any>;

  constructor(private modalService: BsModalService,
              private customerService: CustomerService,
              private productService: ProductService,
              private route: Router,
              private authService: AuthorizationService) {
  }

  ngOnInit(): void {
    this.loadCategories();
  }

  private loadCategories() {
    this.productService.getCategories()
      .subscribe(
        (res: Uiresponse) => {
          this.categoryList = res.body;
        }
      )
  }

  openLogin() {
    this.isLogged = false;
    this.isNotExist = false;
    this.modalRef = this.modalService.show(this.loginTemplate);
  }

  openRegister() {
    this.isRegistered = false;
    this.isExist = false;
    this.modalRef = this.modalService.show(this.registerTemplate);
  }

  isAdmin(): boolean {
    return this.user.role === this.ADMIN;
  }

  isManager(): boolean {
    return this.user.role === this.MANAGER;
  }

  isCustomer(): boolean {
    return this.user.role === this.CUSTOMER;
  }

  isGuest() {
    return this.user.role === undefined;
  }

  submitLogin() {
    this.isLogged = false;
    // if (this.validateInput(this.login, this.password)) {
    //
    //
    // }
    this.authService.login(new AuthorizationData(null, this.login, this.password, null))
      .subscribe(
        (response: Uiresponse) => {
          this.isLogged = response.success;
          this.isNotExist = !response.success;

          this.customerService.setUser(response.body);
          this.user = response.body;
          this.modalRef.hide();
          this.route.navigate(['/about']);
        },
        error2 => {
          this.isNotExist = true;
        }
      );
  }

  logout() {
    this.user = null;
    this.customerService.setUser(null);
    this.route.navigate(['']);
  }

  validateLogin(_login: string, _pass: string): boolean {
    this.isShortLogin = this.isShortPass = this.notEqual = false;
    let flag = true;
    if (_login.length > 1) {
      this.isShortLogin = true;
      flag = false;
    }
    if (_pass.length > 1) {
      this.isShortPass = true;
      flag = false;
    }
    return flag;
  }

  submitRegister() {
    this.authService.register(new AuthorizationData(this.fullName, this.login, this.password, this.email))
      .subscribe(
        (data: Uiresponse) => {
          this.isRegistered = data.success;
          this.isExist = !data.success;
          this.customerService.setUser(data.body);
          this.modalRef.hide();
          this.route.navigate(['/about']);
        },
        error2 => {
          this.isExist = true;
        }
      );
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
}
