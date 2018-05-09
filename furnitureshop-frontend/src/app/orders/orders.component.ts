import {ChangeDetectorRef, Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {Order} from '../../domain/shop/order';
import {ActivatedRoute} from '@angular/router';
import {CustomerService} from '../../service/customer.service';
import {Uiresponse} from '../../domain/uiresponse';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {User} from '../../domain/user/user';
import {OrderDetails} from '../../domain/shop/order-details';
import {ManagerService} from '../../service/manager.service';
import {AppComponent} from '../app.component';
import {Status} from '../../domain/shop/status';
import {UtilService} from '../../service/util.service';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  private user = new User();
  private MANAGER = 'ROLE_MANAGER';
  private CUSTOMER = 'ROLE_USER';

  isDeleted = false;
  orderList: Order[] = new Array(0);
  modalItem: Order;
  orderDetailsList: OrderDetails[];
  statusList: Status[];
  modalRef: BsModalRef;

  @ViewChild('orderModal')
  private orderTemplate: TemplateRef<any>;

  constructor(private route: ActivatedRoute,
              private customerService: CustomerService,
              private app: AppComponent,
              private translate: TranslateService,
              private managerService: ManagerService,
              private modalService: BsModalService,
              private utilService: UtilService,
              private cd: ChangeDetectorRef) {
  }

  ngOnInit() {
    this.user = this.app.user;
    this.loadOrders(this.user.role.title);
  }

  private loadStatus() {
    this.utilService.getAllStatus().subscribe(
      (res: Uiresponse) => {
        this.statusList = res.body;
      }
    )
  }

  private loadOrders(role: string) {
    if (role === this.MANAGER){
      this.loadStatus();
      this.getAllManagerOrders(this.user.id);
    } else {
      this.getAllCustomerOrders(this.user.id);
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
    this.customerService.getOrderInfo(this.user.id, orderId).subscribe(
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
    this.managerService.deleteOrder(this.user.id, orderId).subscribe(
      (res: Uiresponse) => {
        this.isDeleted = res.success;
        if (this.isDeleted){
          this.loadOrders(this.user.role.title);
          this.cd.detectChanges();
        }
      }
    );
  }

  update(order: Order){
    return this.managerService.updateOrder(this.user.id, order)
      .subscribe(
        (res: Uiresponse) => {
          if (res.success){
            this.loadOrders(this.user.role.title);
            this.cd.detectChanges();
          }
        }
      );
  }

  getSum(itemList: OrderDetails[]){
    let sum = 0;
    for(const item of itemList){
      sum = sum + item.total;
    }
    return sum;
  }

  /**
   * A method that returns a price value according current currency
   * @param {number} price
   * @returns {number | string}
   */
  getPriceByLang(price: number): number{
    return this.translate.currentLang === this.app.RU ? price : (price / this.app.COURSE);
  }

  isManager(): boolean {
    return this.user.role.title === this.MANAGER;
  }

  isCustomer(): boolean {
    return this.user.role.title === this.CUSTOMER;
  }

  selected(first: number, second: number){
    if(first === second){
      return 'selected';
    }
    return '';
  }

  isEmpty(list: any[]){
    if(list != null){
      return list.length === 0;
    }
    return false;
  }

  getFinishDate(item: Order, statusId: number){
    if (item.status.id === statusId){
      return item.completionDate;
    }
  }
}
