import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {ProductService} from '../../service/product.service';
import {Router} from '@angular/router';
import {AuthorizationService} from '../../service/authorization.service';
import {CustomerService} from '../../service/customer.service';
import {AuthorizationData} from '../../domain/user/authorization-data';
import {Uiresponse} from '../../domain/uiresponse';
import {User} from '../../domain/user/user';
import {AppComponent} from '../app.component';
import {Role} from '../../domain/user/role';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user = new User();
  login: string;
  password: string;
  isNotExist = false;
  isLogged = false;
  isShortLogin = false;
  isShortPass = false;
  notEqual = false;

  public modalRef: BsModalRef;

  @ViewChild('loginModal')
  private loginTemplate: TemplateRef<any>;

  constructor(private modalService: BsModalService,
              private customerService: CustomerService,
              private productService: ProductService,
              private router: Router,
              private authService: AuthorizationService,
              private app: AppComponent) { }

  ngOnInit() {
    this.user.role = new Role(0, "ROLE_GUEST");
    this.openModal();
  }

  openModal() {
    this.modalRef = this.modalService.show(this.loginTemplate);
  }

  submit() {
    this.isLogged = false;
    if (this.validate(this.login, this.password)) {
      this.log_in();
    }
  }

  private log_in() {
    this.authService.login(new AuthorizationData(null, this.login, this.password, null))
      .subscribe(
        (response: Uiresponse) => {
          this.isLogged = response.success;
          this.isNotExist = !response.success;
          // this.customerService.setUser(response.body);
          this.app.user = response.body;
          this.modalRef.hide();
          this.router.navigate(['/']);
        },
        error2 => {
          this.isNotExist = true;
        }
      );
  }

  validate(_login: string, _pass: string): boolean {
    this.isShortLogin = this.isShortPass = this.notEqual = false;
    let flag = true;
    if (_login.length < 1) {
      this.isShortLogin = true;
      flag = false;
    }
    if (_pass.length < 1) {
      this.isShortPass = true;
      flag = false;
    }
    return flag;
  }

  close(){
    this.modalRef.hide();
    this.router.navigate(['/']);
  }
}
