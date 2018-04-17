export class Product {
  private _id: number;
  private _category: string;
  private _code: string;
  private _name: string;
  private _description: string;
  private _manufacturer: string;
  private _price: number;
  private _imagesUrl: string[];


  constructor(code: string, name: string, price: number, manufacturer?: string, imagesUrl?: string[]) {
    this._code = code;
    this._name = name;
    this._manufacturer = manufacturer;
    this._imagesUrl = imagesUrl;
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

  get code(): string {
    return this._code;
  }

  set code(value: string) {
    this._code = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get category(): string {
    return this._category;
  }

  set category(value: string) {
    this._category = value;
  }

  get manufacturer(): string {
    return this._manufacturer;
  }

  set manufacturer(value: string) {
    this._manufacturer = value;
  }

  get imagesUrl(): string[] {
    return this._imagesUrl;
  }

  set imagesUrl(value: string[]) {
    this._imagesUrl = value;
  }

  get price(): number {
    return this._price;
  }

  set price(value: number) {
    this._price = value;
  }
}
