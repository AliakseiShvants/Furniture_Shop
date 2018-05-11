import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../domain/user/user';
import {CustomerService} from '../../service/customer.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Uiresponse} from '../../domain/uiresponse';
import {Category} from '../../domain/product/category';
import {ProductService} from '../../service/product.service';
import {AppComponent} from '../app.component';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  @Input()
  user: User;
  categoryList: Category[];

  private CUSTOMER = 'ROLE_USER';
  private ADMIN = 'ROLE_ADMIN';
  private MANAGER = 'ROLE_MANAGER';
  private GUEST = 'ROLE_GUEST';

  constructor(private customerService: CustomerService,
              private productService: ProductService,
              private router: Router,
              private route: ActivatedRoute,
              private app: AppComponent) {
    this.loadCategories();
  }

  ngOnInit(): void {
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
    this.app.user = new User(0);
    this.router.navigate(['']);
  }

  showBasketCount(){
    if (this.user.id > 0){
      return this.app.basketList.length;
    } else {
      return this.app.guestBasketList.length;
    }
  }

  isBasketEmpty() {
    if (this.user.id > 0) {
      return this.app.basketList.length === 0;
    } else {
      return this.app.guestBasketList.length === 0;
    }
  }
}
