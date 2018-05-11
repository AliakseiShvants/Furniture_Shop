import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {GoodPriceComponent} from '../app/good-price/good-price.component';

/**
 * A service class for sending HTTP requests to app backend.
 */
@Injectable()
export class StorageService {

  private base = 'api/storage/';
  private product = 'product/';

  private cheapList = 'cheapList/';

  updateCheapList = new EventEmitter<string>();

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
  getCheapProductList(lang: string){
    return this.http.get(this.base + this.product + this.cheapList + lang);
  }
}
