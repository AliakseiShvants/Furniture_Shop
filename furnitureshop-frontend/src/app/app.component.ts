 import {Component, OnInit, Output} from '@angular/core';
 import {TranslateService} from '@ngx-translate/core';
 import {Role} from '../domain/user/role';
 import {User} from '../domain/user/user';
 import {AppService} from '../service/app.service';
 import {CustomerService} from '../service/customer.service';
 import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';
 import {AuthorizationService} from '../service/authorization.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private translate: TranslateService) {
    translate.setDefaultLang('ru');
  }

  switchLang(lang: string) {
    this.translate.use(lang);
  }

  ngOnInit() {
  }

}

