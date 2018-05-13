import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {User} from '../../domain/user/user';
import {AdminService} from '../../service/admin.service';
import {Uiresponse} from '../../domain/uiresponse';
import {Requisite} from '../../domain/shop/requisite';
import {AuthorizationData} from '../../domain/user/authorization-data';
import {Role} from '../../domain/user/role';
import {UtilService} from '../../service/util.service';

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
  tempCustomer = new User();
  roleList: Role[];
  success = false;
  errorMessage: string;
  isDeleted = false;

  constructor(private adminService: AdminService,
              private utilService: UtilService,
              private cd: ChangeDetectorRef) {

    this.tempCustomer.requisite = new Requisite();
  }

  ngOnInit() {
    this.loadRoles();
    this.loadCustomers();
  }

  private loadRoles() {
    this.utilService.getAllRoles().subscribe(
      (res: Uiresponse) => {
        this.roleList = res.body;
      }
    )
  }

  private loadCustomers() {

    this.adminService.getAllCustomers()
      .subscribe(
        (data: Uiresponse) => {
          this.success = data.success;
          this.customers = data.body;
        }
      );
  }

  addNewCustomer() {
    this.adminService.addNewCustomer(new AuthorizationData(this.fullName, this.login, this.password))
      .subscribe(
        (data: Uiresponse) => {
          this.success = data.success;
        }
      );
  }

  getRequisite(requisite: Requisite) {
    return requisite.zip + ', ' + requisite.country + ', ' + requisite.city + ', ' + requisite.address;
  }

  prepareEditCustomer(customer: User){
    this.tempCustomer = customer;
  }

  updateCustomer(customer: User){
    this.adminService.updateUser(customer)
      .subscribe(
        (res: Uiresponse) => {
          if (res.success){
            this.loadCustomers();
            this.cd.detectChanges();
          }
        }
      )
  }

  prepareDeleteCustomer(customer: User){
    this.tempCustomer = customer;
  }

  deleteCustomer(customerId: number){
    this.adminService.deleteCustomer(customerId)
      .subscribe(
        (res: Uiresponse) => {
          this.isDeleted = res.success;
          if (this.isDeleted){
            this.loadCustomers();
            this.cd.detectChanges();
          }
        }
      )
  }

  prepareEditRequisite(customer: User){
    this.tempCustomer = customer;
  }

  updateRequisite(requisite: Requisite){
    this.adminService.updateRequisite(requisite)
      .subscribe(
        (res: Uiresponse) => {
          if (res.success){
            this.loadCustomers();
            this.cd.detectChanges();
          }
        }
      )
  }

  getSex(user: User){
    if (user.sex){
      return 'checked';
    }
    return '';
  }

  selected(first: any, second: any){
    if (first === second){
      return 'selected';
    }
  }

  close(){
    this.success = false;
  }
}
