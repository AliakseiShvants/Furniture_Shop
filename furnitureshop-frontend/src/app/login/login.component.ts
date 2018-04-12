import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {CustomerService} from '../../service/customer.service';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {Customer} from '../../domain/user/customer';
import {HttpResponse} from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: string;
  password: string;

  customer: Customer;

  isLogged = false;
  isNotExist = false;
  isShortLogin = false;
  isShortPass = false;
  notEqual = false;

  modalRef: BsModalRef;

  @ViewChild('loginModal')
  private template: TemplateRef<any>;

  constructor(private customerService: CustomerService, private modalService: BsModalService) { }

  ngOnInit() {
    this.openModal(this.template);
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  submit() {
    // if (this.validateInput(this.login, this.password)) {
    //
    //
    // }
    this.customerService.login(this.login, this.password)
      .subscribe(
        (response: HttpResponse<Customer>) => {
          this.customer = response.body;
          this.isLogged = true;
          console.log(this.customer.fullName);
        },
        error2 => {
          console.log('customer is not found');
          this.isNotExist = true;
          console.log('fail');
        }
      );
    // this.customerService.mockLogin(this.login, this.password)
    //   .subscribe(
    //     (data: Customer) => {
    //       this.customer = data;
    //       console.log(this.customer.fullName);
    //     },
    //     error => {
    //       console.log(error);
    //     }
    //   );

  }

  validateInput(_login: string, _pass: string): boolean {
    this.isShortLogin = this.isShortPass = this.notEqual = false;
    let flag = true;
    if (_login.length > 1) {
      this.isShortLogin = true;
      flag = false;
    }
    if (_pass.length > 1) {
      this.isShortPass = true;
      flag = false;
    }
    return flag;
  }

}
