import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class ProductService {

  private product = 'api/product/';
  private category = 'category/';
  private categories = 'categories';

  constructor(private http: HttpClient) { }

  getStorageItemsByProductCategory(category: string){
    return this.http.get(this.product + category);
  }

  getCategories() {
    return this.http.get(this.product + this.categories);
  }

  getCategory(title: string) {
    return this.http.get(this.product + this.category + title);
  }
}
