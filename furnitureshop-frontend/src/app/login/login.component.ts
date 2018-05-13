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

  login = '';
  password= '';
  isNotExist = false;
  isLogged = false;

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
    this.openModal();
  }

  private openModal() {
    this.modalRef = this.modalService.show(this.loginTemplate);
  }

  submit() {
    this.isLogged = false;
    this.log_in();
  }

  private log_in() {
    this.authService.login(new AuthorizationData(null, this.login, this.password))
      .subscribe(
        (response: Uiresponse) => {
          this.isLogged = response.success;
          this.isNotExist = !response.success;

          if (this.isLogged){
            this.app.user = response.body;
            this.app.loadUserBasket(this.app.user.id);

            setTimeout(() => {
              this.modalRef.hide();

              if (!this.app.isEmpty(this.app.basketList) || !this.app.isEmpty(this.app.guestBasketList)){
                this.router.navigate(['/basket']);
              } else {
                this.router.navigate(['/']);
              }
            }, 1500);
          }
        }
      );
  }

  close(){
    this.modalRef.hide();
    this.router.navigate(['/']);
  }
}
