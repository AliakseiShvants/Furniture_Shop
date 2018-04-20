import { Component, OnInit } from '@angular/core';
import {User} from '../../domain/user/user';
import {CustomerService} from '../../service/customer.service';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  user: User;
  constructor(private customerService: CustomerService) { }

  ngOnInit() {
    this.user = this.customerService.getUser();
  }

}
