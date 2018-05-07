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
import {Manufacturer} from '../../domain/product/manufacturer';
import {AppComponent} from '../app.component';

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
  public product = new Product();
  public categoryList: Category[];
  public manufacturerList: Manufacturer[];
  public modalRef: BsModalRef;
  public isAdded= false;
  public isDeleted = false;

  constructor(private route: ActivatedRoute,
              private customerService: CustomerService,
              private app: AppComponent,
              private managerService: ManagerService,
              private productService: ProductService,
              private modalService: BsModalService,
              private cd: ChangeDetectorRef) {

  }

  ngOnInit() {
    this.user = this.app.user;
    this.userId = this.route.snapshot.params['id'];
    this.loadCategories();
    this.loadManufacturers();
    this.loadProducts(this.userId);
    this.product.category = new Category();
    this.product.manufacturer = new Manufacturer();
  }

  private loadManufacturers() {
    this.productService.getManufacturers()
      .subscribe(
        (res: Uiresponse) => {
          this.manufacturerList = res.body;
        }
      )
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

  openAddProductModal(){
    this.modalRef = this.modalService.show(this.productTemplate);
  }

  openUpdateProductModal(product: Product){
    this.product = product;
    this.modalRef = this.modalService.show(this.productTemplate);
  }

  isEmpty(list: any[]){
    return list.length === 0;
  }

  isRequisiteExists(item: Product){
    return item.manufacturer !== null;
  }

  addRequisite(item: Product){
    this.productService.addManufacturer(item.id, item.manufacturer)
      .subscribe()
  }

  addProduct(managerId: number, product: Product){
    this.managerService.addProduct(managerId, product)
      .subscribe(
        (res: Uiresponse) => {
          this.isAdded = res.success;
          this.product = res.body;
          this.productList.push(product);
        }
      );
  }

  updateProduct(product: Product){
    this.managerService.updateProduct(this.userId, product)
      .subscribe(
        (res: Uiresponse) => {
          if (res.success){
            this.loadProducts(this.userId);
            this.cd.detectChanges();
          }
        }
      )
  }

  prepareDeleteProduct(product: Product){
    this.product = product;
  }

  deleteProduct(productId: number){
    this.managerService.deleteProduct(this.userId, productId)
      .subscribe(
        (res: Uiresponse) => {
          this.isDeleted = res.success;
          if (this.isDeleted){
            this.loadProducts(this.userId);
            this.cd.detectChanges();
          }
        }
      )
  }

  selected(first: any, second: any){
    if (first === second){
      return 'selected';
    }
  }

  isProductExists(product: Product){
    return product.id > 0;
  }

}
