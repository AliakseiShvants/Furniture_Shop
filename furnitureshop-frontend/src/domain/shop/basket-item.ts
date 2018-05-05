import {Product} from '../product/product';

export class BasketItem {
  id: number;
  product: Product;
  quantity: number;
  price: number;

  constructor(product: Product, quantity: number, price: number, id?: number,) {
    this.id = id;
    this.product = product;
    this.quantity = quantity;
    this.price = price;
  }
}
