import {Product} from '../product/product';
import {User} from '../user/user';

export class Storage {

  id: number;
  code: string;
  product: Product;
  manager: User;
  price: number;
  quantity: number;


  constructor(id: number, code: string, product: Product, manager: User, price: number, quantity: number) {
    this.id = id;
    this.code = code;
    this.product = product;
    this.manager = manager;
    this.price = price;
    this.quantity = quantity;
  }
}
