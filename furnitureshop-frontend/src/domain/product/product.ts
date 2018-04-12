import {Category} from './category';
import {Manufacturer} from './manufacturer';
import {Image} from './image';

export class Product {

  private _id: number;
  private _name: string;
  private _description: string;
  private _category: Category;
  private _manufacturer: Manufacturer;
  private _images: Image[];


  constructor(name: string, description: string, manufacturer: Manufacturer, images: Image[]) {
    this._name = name;
    this._description = description;
    this._manufacturer = manufacturer;
    this._images = images;
  }

  get images(): Image[] {
    return this._images;
  }

  set images(value: Image[]) {
    this._images = value;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get category(): Category {
    return this._category;
  }

  set category(value: Category) {
    this._category = value;
  }

  get manufacturer(): Manufacturer {
    return this._manufacturer;
  }

  set manufacturer(value: Manufacturer) {
    this._manufacturer = value;
  }
}
