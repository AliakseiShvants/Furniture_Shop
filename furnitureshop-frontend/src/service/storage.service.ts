import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class StorageService {

  private base = 'api/storage/';
  private product = 'product/';

  private cheapList = 'cheapList';

  constructor(private http: HttpClient) { }

  getStorageListByProductCategory(category: string){
    return this.http.get(this.base + this.product + category);
  }

  getCheapProductList(){
    return this.http.get(this.base + this.product + this.cheapList);
  }
}
