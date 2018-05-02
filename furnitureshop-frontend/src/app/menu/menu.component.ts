import {ChangeDetectorRef, Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {User} from '../../domain/user/user';
import {CustomerService} from '../../service/customer.service';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {AuthorizationData} from '../../domain/user/authorization-data';
import {Uiresponse} from '../../domain/uiresponse';
import {AuthorizationService} from '../../service/authorization.service';
import {Category} from '../../domain/product/category';
import {ProductService} from '../../service/product.service';
import {Subscription} from 'rxjs/Subscription';
import {AppComponent} from '../app.component';
import {Role} from '../../domain/user/role';
import {UtilService} from '../../service/util.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  user = new User();
  categoryList: Category[];
  roleList: Role[];

  fullName: string;
  login: string;
  password: string;
  email: string;
  confirmPassword: string;
  isShortLogin = false;
  isShortPass = false;
  isRegistered = false;
  isExist = false;
  notEqual = false;
  isShortName = false;
  isIncorrectEmail = false;

  private subscription: Subscription;
  private CUSTOMER = 'ROLE_USER';
  private ADMIN = 'ROLE_ADMIN';
  private MANAGER = 'ROLE_MANAGER';
  private GUEST = 'ROLE_GUEST';

  modalRef: BsModalRef;

  @ViewChild('registerModal')
  private registerTemplate: TemplateRef<any>;

  constructor(private modalService: BsModalService,
              private customerService: CustomerService,
              private productService: ProductService,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthorizationService,
              private app: AppComponent,
              private utilService: UtilService) {


    setInterval(() => {
      this.user = this.app.user;
    }, 2000)
  }

  ngOnInit(): void {
    // this.user = this.customerService.getUser();
    this.user = this.app.user;
    this.loadCategories();
    // this.loadRoles();
  }

  // private loadRoles() {
  //   this.utilService.getAllRoles()
  //     .subscribe(
  //       (res: Uiresponse) => {
  //         this.roleList = res.body;
  //       }
  //     )
  // }

  private loadCategories() {
    this.productService.getCategories()
      .subscribe(
        (res: Uiresponse) => {
          this.categoryList = res.body;
        }
      );
  }

  openRegister() {
    this.isRegistered = false;
    this.isExist = false;
    this.modalRef = this.modalService.show(this.registerTemplate);
  }

  isAdmin(): boolean {
    if (this.user.role !== null){
      return this.user.role.title === this.ADMIN;
    }
    return false;
  }

  isManager(): boolean {
    if (this.user.role !== null){
      return this.user.role.title === this.MANAGER;
    }
    return false;
  }

  isCustomer(): boolean {
    return this.user.role.title === this.CUSTOMER;
  }

  isGuest(): boolean {
    return this.user.role.title === this.GUEST;
  }

  logout() {
    this.authService.logout();
    this.user = null;
    this.customerService.setUser(null);
    this.router.navigate(['']);
  }

  submitRegister() {

    this.authService.register(new AuthorizationData(this.fullName, this.login, this.password, this.email))
      .subscribe(
        (data: Uiresponse) => {
          this.isRegistered = data.success;
          this.isExist = !data.success;
          this.customerService.setUser(data.body);
          this.modalRef.hide();
          this.router.navigate(['/about']);
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
