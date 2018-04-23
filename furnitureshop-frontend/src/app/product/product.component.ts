import {ChangeDetectorRef, Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {User} from '../../domain/user/user';
import {ActivatedRoute} from '@angular/router';
import {CustomerService} from '../../service/customer.service';
import {ManagerService} from '../../service/manager.service';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {Product} from '../../domain/product/product';
import {ProductService} from '../../service/product.service';
import {Uiresponse} from '../../domain/uiresponse';
import {Category} from '../../domain/product/category';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  private user: User;
  private userId: number;

  @ViewChild('productModal')
  private productTemplate: TemplateRef<any>;

  public productList: Product[];
  public categoryList: Category[];
  public product: Product;
  public modalRef: BsModalRef;
  public isProductAdded= false;

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
    this.loadCategories();
    this.loadProducts(this.userId);
  }

  private loadCategories() {
    this.productService.getCategories()
      .subscribe(
        (res: Uiresponse) => {
          this.categoryList = res.body;
        }
      )
  }

  private loadProducts(userId: number) {
    this.managerService.getManagerProducts(userId)
      .subscribe(
        (res: Uiresponse) => {
          this.productList = res.body;
        }
      );
  }

  openModal(){
    this.modalRef = this.modalService.show(this.productTemplate);
  }

  isEmpty(list: any[]){
    return list.length === 0;
  }

  isRequisiteExists(item: Product){
    return item.manufacturer !== null;
  }

  addRequisite(item: Product){

  }

  addProduct(){





    this.product = null;

  }

  update(item: Product){

  }

  delete(item: Product){

  }



}
