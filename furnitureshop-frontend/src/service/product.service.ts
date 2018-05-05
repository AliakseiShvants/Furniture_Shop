import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Manufacturer} from '../domain/product/manufacturer';

@Injectable()
export class ProductService {

  private product = 'api/product/';
  private category = 'category/';
  private categories = 'categories';
  private manufacturer = 'manufacturer';
  private add = '/add';
  private all = '/all';

  constructor(private http: HttpClient) { }

  getCategories() {
    return this.http.get(this.product + this.categories);
  }

  getCategory(title: string) {
    return this.http.get(this.product + this.category + title);
  }

  addManufacturer(productId: number, manufacturer: Manufacturer){
    return this.http.post(this.product + productId + this.manufacturer + this.add, manufacturer);
  }

  getManufacturers() {
    return this.http.get(this.product + this.manufacturer + this.all);
  }
}
