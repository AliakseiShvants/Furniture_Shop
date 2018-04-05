import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {AboutComponent} from '../about/about.component';
import {ViewerComponent} from '../viewer/viewer.component';
import {RegisterComponent} from '../register/register.component';

export const appRoutes: Routes = [
  {path: 'about', component: AboutComponent},
  {path: 'view', component: ViewerComponent},
  {path: 'register', component: RegisterComponent},
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
