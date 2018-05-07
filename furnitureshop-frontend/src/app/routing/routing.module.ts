import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ViewerComponent} from '../viewer/viewer.component';
import {ProfileComponent} from '../profile/profile.component';
import {OrdersComponent} from '../orders/orders.component';
import {BasketComponent} from '../basket/basket.component';
import {CustomerComponent} from '../customer/customer.component';
import {StorageComponent} from '../storage/storage.component';
import {ProductComponent} from '../product/product.component';
import {LoginComponent} from '../login/login.component';
import {ManagerComponent} from '../manager/manager.component';
import {GoodPriceComponent} from '../good-price/good-price.component';
import {RegisterComponent} from '../register/register.component';

export const appRoutes: Routes = [
  {path: 'good-price', component: GoodPriceComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},

  {path: 'viewer/:category', component: ViewerComponent},

  {path: 'customers', component: CustomerComponent},
  {path: 'managers', component: ManagerComponent},

  {path: 'profile', component: ProfileComponent},
  {path: 'profile/:form', component: ProfileComponent},

  {path: 'orders', component: OrdersComponent},
  {path: 'storage', component: StorageComponent},
  {path: 'products', component: ProductComponent},

  {path: 'basket', component: BasketComponent},

  {path: '',   redirectTo: '/good-price', pathMatch: 'full'},
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(appRoutes)
  ],
  exports: [RouterModule],
  declarations: []
})
export class RoutingModule { }
