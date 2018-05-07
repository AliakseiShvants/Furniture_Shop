import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

/**
 * A service class for sending HTTP requests to app backend.
 */
@Injectable()
export class StorageService {

  private base = 'api/storage/';
  private product = 'product/';

  private cheapList = 'cheapList';

  constructor(private http: HttpClient) { }

  /**
   * A method that gets a list of {@link Storage} entities with {@link Category} title equals @param.
   * @param {string} category title field of {@link Category} entity
   * @returns {Observable<Object>}
   */
  getStorageListByProductCategory(category: string){
    return this.http.get(this.base + this.product + category);
  }

  /**
   * A method that gets a list of {@link Storage} entities contains four entities of each category.
   * @returns {Observable<Object>} a list of {@link Storage} entities
   */
  getCheapProductList(){
    return this.http.get(this.base + this.product + this.cheapList);
  }
}
