import {Component, OnInit} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {User} from '../domain/user/user';
import {Role} from '../domain/user/role';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  user = new User();

  constructor(private translate: TranslateService) {
    translate.setDefaultLang('ru');
  }

  switchLang(lang: string) {
    this.translate.use(lang);
  }

  ngOnInit() {
    this.user.role = new Role(0, "ROLE_GUEST");
  }

}

