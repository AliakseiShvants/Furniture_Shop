import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {AboutComponent} from '../about/about.component';
import {ViewerComponent} from '../viewer/viewer.component';
import {ProfileComponent} from '../profile/profile.component';
import {OrdersComponent} from '../orders/orders.component';
import {BasketComponent} from '../basket/basket.component';
import {CustomerComponent} from '../customer/customer.component';
import {StorageComponent} from '../storage/storage.component';
import {ProductComponent} from '../product/product.component';
import {LoginComponent} from '../login/login.component';
import {ManagerComponent} from '../manager/manager.component';

export const appRoutes: Routes = [
  {path: 'about', component: AboutComponent},
  {path: 'login', component: LoginComponent},

  {path: 'viewer/:category', component: ViewerComponent},

  {path: 'customers', component: CustomerComponent},
  {path: 'managers', component: ManagerComponent},

  {path: 'profile/:id', component: ProfileComponent},
  {path: 'orders/:id', component: OrdersComponent},
  {path: 'storage/:id', component: StorageComponent},
  {path: 'products/:id', component: ProductComponent},
  {path: 'basket/:id', component: BasketComponent},


  { path: '',   redirectTo: '/about', pathMatch: 'full' },
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
