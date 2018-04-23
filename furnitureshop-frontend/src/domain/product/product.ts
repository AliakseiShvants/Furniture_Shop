import {Category} from './category';
import {Manufacturer} from './manufacturer';

export class Product {

  public id: number;
  public category: Category;
  public name: string;
  public description: string;
  public manufacturer: Manufacturer;
  public url: string;

}
