import { Component, OnInit } from '@angular/core';
import {User} from '../../domain/user/user';
import {AdminService} from '../../service/admin.service';
import {Uiresponse} from '../../domain/uiresponse';
import {Requisite} from '../../domain/shop/requisite';
import {AuthorizationData} from '../../domain/user/authorization-data';
import {CustomerService} from '../../service/customer.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  fullName: string;
  login: string;
  password: string;
  email: string;

  customers: User[];
  success: boolean;
  errorMessage: string;

  constructor(private customerService: CustomerService) { }

  ngOnInit() {
    this.loadCustomers();
  }

  private loadCustomers() {
    this.success = false;
    this.customerService.getAllCustomers()
      .subscribe(
        (data: Uiresponse) => {
          this.success = data.success;
          this.customers = data.body;
        }
      );
  }

  addNewCustomer() {
    this.success = false;
    this.customerService.addNewCustomer(new AuthorizationData(this.fullName, this.login, this.password, this.email))
      .subscribe(
        (data: Uiresponse) => {
          this.success = data.success;
          this.errorMessage = data.exception;
        }
      );
  }

  getRequisite(requisite: Requisite) {
    return requisite.zip + ', ' + requisite.country + ', ' + requisite.city + ', ' + requisite.address;
  }
}
