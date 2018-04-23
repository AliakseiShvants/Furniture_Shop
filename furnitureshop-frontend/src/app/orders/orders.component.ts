import {ChangeDetectorRef, Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {Order} from '../../domain/shop/order';
import {ActivatedRoute} from '@angular/router';
import {CustomerService} from '../../service/customer.service';
import {Uiresponse} from '../../domain/uiresponse';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {User} from '../../domain/user/user';
import {OrderDetails} from '../../domain/shop/order-details';
import {ManagerService} from '../../service/manager.service';
import Order = jasmine.Order;
import {ProductService} from '../../service/product.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  private user: User;
  private userId: number;
  private MANAGER = 'ROLE_MANAGER';
  private CUSTOMER = 'ROLE_USER';

  public isDeleted = false;
  public orderList: Order[];
  public modalItem: Order;
  public orderDetailsList: OrderDetails[];
  public modalRef: BsModalRef;

  public IN_PROC = 'IN_PROCESSING';
  public ACCEPT = 'ACCEPT';
  public SENT = 'SENT';

  @ViewChild('orderModal')
  private orderTemplate: TemplateRef<any>;

  constructor(private route: ActivatedRoute,
              private customerService: CustomerService,
              private managerService: ManagerService,
              private modalService: BsModalService,
              private cd: ChangeDetectorRef) {

    // setInterval(() => {
    //   this.loadOrders(this.user.role);
    //   this.cd.detectChanges();
    // }, 1000);
  }

  ngOnInit() {
    this.user = this.customerService.getUser();
    this.userId = this.route.snapshot.params['id'];
    this.loadStatus();
    this.loadOrders(this.user.role);
  }

  private loadStatus() {
    //todo load from db
  }

  private loadOrders(role: string) {
    if (role === this.MANAGER){
      this.getAllManagerOrders(this.userId);
    } else {
      this.getAllCustomerOrders(this.userId);
    }
  }

  private getAllManagerOrders(userId: number) {
    this.managerService.getAllManagerOrders(userId).subscribe(
      (res: Uiresponse) => {
        this.orderList = res.body;
      }
    )
  }

  openOrderDetails(item: Order){
    this.modalItem = item;
    this.getOrderInfo(item.id);
    this.modalRef = this.modalService.show(this.orderTemplate);
  }

  private getOrderInfo(orderId: number) {
    this.customerService.getOrderInfo(this.userId, orderId).subscribe(
      (res: Uiresponse) => {
        this.orderDetailsList = res.body;
      }
    )
  }

  private getAllCustomerOrders(userId: number) {
    this.customerService.getAllCustomerOrders(userId).subscribe(
      (res: Uiresponse) => {
        this.orderList = res.body;
      }
    )
  }

  delete(orderId: number){
    this.managerService.deleteOrder(this.userId, orderId).subscribe(
      (res: Uiresponse) => {
        this.isDeleted = res.success;
        if (this.isDeleted){
          this.loadOrders(this.user.role);
          this.cd.detectChanges();
        }
      }
    );
  }

  update(order: Order){
    return this.managerService.updateOrder(this.userId, order)
      .subscribe(
        (res: Uiresponse) => {
          if (res.success){
            this.loadOrders(this.user.role);
            this.cd.detectChanges();
          }
        }
      );
  }

  totalSum(itemList: OrderDetails[]){
    let sum = 0;
    for(const item of itemList){
      sum = sum + item.quantity * item.price;
    }
    return sum;
  }

  isManager(): boolean {
    return this.user.role === this.MANAGER;
  }

  isCustomer(): boolean {
    return this.user.role === this.CUSTOMER;
  }

  selected(itemStatus: string, status: string){
    if(itemStatus === status){
      return 'selected';
    }
  }

  isEmpty(list: any[]){
    return list.length === 0;
  }

}
