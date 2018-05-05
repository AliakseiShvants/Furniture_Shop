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

  private CUSTOMER = 'ROLE_USER';
  private ADMIN = 'ROLE_ADMIN';
  private MANAGER = 'ROLE_MANAGER';
  private GUEST = 'ROLE_GUEST';

  constructor(private customerService: CustomerService,
              private productService: ProductService,
              private router: Router,
              private route: ActivatedRoute,
              private app: AppComponent,
              private utilService: UtilService) {


    setInterval(() => {
      this.user = this.app.user;
    }, 2000)
  }

  ngOnInit(): void {
    this.user = this.app.user;
    this.loadCategories();
  }

  private loadCategories() {
    this.productService.getCategories()
      .subscribe(
        (res: Uiresponse) => {
          this.categoryList = res.body;
        }
      );
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
    this.user = null;
    this.customerService.setUser(null);
    this.router.navigate(['']);
  }
}
