import {Product} from '../product/product';

export class OrderDetails {
  id: number;
  code: string;
  product: Product;
  quantity: number;
  price: number;
}
