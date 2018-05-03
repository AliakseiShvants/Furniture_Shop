import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {FooterComponent} from './footer/footer.component';
import {HeaderComponent} from './header/header.component';
import {RoutingModule} from './routing/routing.module';
import {MenuComponent} from './menu/menu.component';
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
import {CustomerComponent} from './customer/customer.component';
import {AuthorizationService} from '../service/authorization.service';
import {ProductService} from '../service/product.service';
import {ManagerService} from '../service/manager.service';
import {ProductComponent} from './product/product.component';
import { StorageComponent } from './storage/storage.component';
import { LoginComponent } from './login/login.component';
import { ManagerComponent } from './manager/manager.component';
import {AdminService} from '../service/admin.service';
import {UtilService} from '../service/util.service';
import { GoodPriceComponent } from './good-price/good-price.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    MenuComponent,
    ViewerComponent,
    ProfileComponent,
    OrdersComponent,
    BasketComponent,
    CustomerComponent,
    ProductComponent,
    StorageComponent,
    LoginComponent,
    ManagerComponent,
    GoodPriceComponent,
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
    ProductService,
    CustomerService,
    ManagerService,
    AdminService,
    UtilService,
    AuthorizationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}
