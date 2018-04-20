import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {FooterComponent} from './footer/footer.component';
import {HeaderComponent} from './header/header.component';
import {RoutingModule} from './routing/routing.module';
import {MenuComponent} from './menu/menu.component';
import {AboutComponent} from './about/about.component';
import {ViewerComponent} from './viewer/viewer.component';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {AlertModule, ModalModule} from 'ngx-bootstrap';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {FormsModule} from '@angular/forms';
import {CustomerService} from '../service/customer.service';
import {ProfileComponent} from './profile/profile.component';
import {OrdersComponent} from './orders/orders.component';
import {BasketComponent} from './basket/basket.component';
import {UserComponent} from './user/user.component';
import {CustomerComponent} from './customer/customer.component';
import {ManagersComponent} from './managers/managers.component';
import {AuthorizationService} from '../service/authorization.service';
import {AppService} from '../service/app.service';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    MenuComponent,
    AboutComponent,
    ViewerComponent,
    ProfileComponent,
    OrdersComponent,
    BasketComponent,
    UserComponent,
    CustomerComponent,
    ManagersComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RoutingModule,
    HttpClientModule,
    AlertModule.forRoot(),
    ModalModule.forRoot(),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  providers: [
    AppService,
    CustomerService,
    AuthorizationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}
