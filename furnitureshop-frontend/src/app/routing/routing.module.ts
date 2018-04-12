import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {AboutComponent} from '../about/about.component';
import {ViewerComponent} from '../viewer/viewer.component';
import {RegisterComponent} from '../register/register.component';
import {ChairComponent} from '../chair/chair.component';
import {LoginComponent} from '../login/login.component';
import {DoorComponent} from '../door/door.component';
import {ProfileComponent} from '../profile/profile.component';
import {OrdersComponent} from '../orders/orders.component';
import {BasketComponent} from '../basket/basket.component';
import {BedComponent} from '../bed/bed.component';
import {TableComponent} from '../table/table.component';
import {DresserComponent} from '../dresser/dresser.component';

export const appRoutes: Routes = [
  {path: 'about', component: AboutComponent},

  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},

  {path: 'viewer', component: ViewerComponent},

  {path: 'doors', component: DoorComponent},
  {path: 'beds', component: BedComponent},
  {path: 'tables', component: TableComponent},
  {path: 'chairs', component: ChairComponent},
  {path: 'dressers', component: DresserComponent},

  {path: 'profile', component: ProfileComponent},
  {path: 'orders', component: OrdersComponent},
  {path: 'basket', component: BasketComponent},


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
