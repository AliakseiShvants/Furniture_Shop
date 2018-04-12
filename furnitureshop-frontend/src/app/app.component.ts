 import {Component, OnInit} from '@angular/core';
 import {TranslateService} from '@ngx-translate/core';
 import {Customer} from '../domain/user/customer';
 import {Role} from '../domain/user/role';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  customer: Customer;
  role: Role;

  constructor(private translate: TranslateService) {
    translate.setDefaultLang('ru');
  }

  switchLang(lang: string) {
    this.translate.use(lang);
  }

  ngOnInit(): void {
    this.role = new Role('ROLE_USER');
    this.customer = new Customer();
    this.customer.role = this.role;
  }
}

