import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class ProductService {

  private products = 'api/products/';

  constructor(private http: HttpClient) { }

  getStorageItemsByProductCategory(category: string){
    return this.http.get(this.products + category);
  }

}
