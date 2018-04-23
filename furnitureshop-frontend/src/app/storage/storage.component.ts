import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {User} from '../../domain/user/user';
import {ActivatedRoute} from '@angular/router';
import {ProductService} from '../../service/product.service';
import {CustomerService} from '../../service/customer.service';
import {ManagerService} from '../../service/manager.service';
import {BsModalService} from 'ngx-bootstrap';
import {Storage} from '../../domain/shop/storage';
import {Uiresponse} from '../../domain/uiresponse';

@Component({
  selector: 'app-storage',
  templateUrl: './storage.component.html',
  styleUrls: ['./storage.component.css']
})
export class StorageComponent implements OnInit {

  private user: User;
  private userId: number;
  public storage: Storage[];

  constructor(private route: ActivatedRoute,
              private customerService: CustomerService,
              private managerService: ManagerService,
              private productService: ProductService,
              private modalService: BsModalService,
              private cd: ChangeDetectorRef) {

  }

  ngOnInit() {
    this.user = this.customerService.getUser();
    this.userId = this.route.snapshot.params['id'];
    this.loadStorage(this.userId);
  }

  private loadStorage(userId: number) {
    this.managerService.getManagerStorage(this.userId)
      .subscribe(
        (res: Uiresponse) => {
          this.storage = res.body;
          this.cd.detectChanges();
        }
      )
  }

  isEmpty(list: any[]){
    return list.length === 0;
  }


}
