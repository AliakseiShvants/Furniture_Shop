import {Product} from '../product/product';

export class BasketItem {
  private _id: number;
  private _product: Product;
  private _quantity: number;
  // private _price: number;


  constructor(id: number, product: Product, quantity: number, /*price: number*/) {
    this._id = id;
    this._product = product;
    this._quantity = quantity;
    /*this._price = price;*/
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get product(): Product {
    return this._product;
  }

  set product(value: Product) {
    this._product = value;
  }

  get quantity(): number {
    return this._quantity;
  }

  set quantity(value: number) {
    this._quantity = value;
  }

  // get price(): number {
  //   return this._price;
  // }
  //
  // set price(value: number) {
  //   this._price = value;
  // }
}
