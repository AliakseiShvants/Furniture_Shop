import {Product} from '../product/product';
import {User} from '../user/user';

export class Storage {

  id: number;
  code: string;
  product: Product;
  manager: User;
  price: number;
  quantity: number;

}
