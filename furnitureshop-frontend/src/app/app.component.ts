import {Component, OnInit} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {User} from '../domain/user/user';
import {Role} from '../domain/user/role';
import {Storage} from '../domain/shop/storage';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  private RU = 'ru';
  user = new User();
  productsInBasket: number[] = new Array(0);
  guestBasket: Storage[] = new Array(0);

  constructor(private translate: TranslateService) {
    translate.setDefaultLang(this.RU);
  }

  /**
   * A method that enables language
   * @param {string} lang a language code
   */
  switchLang(lang: string) {
    this.translate.use(lang);
  }

  ngOnInit() {
    this.user.id = 0;
    this.user.role = new Role(0, 'ROLE_GUEST');
  }

}

