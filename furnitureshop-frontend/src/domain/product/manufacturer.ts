import {Requisite} from '../shop/requisite';

export class Manufacturer {

  private _id: number;
  private _title: string;


  constructor(title: string) {
    this._title = title;
  }

  get title(): string {
    return this._title;
  }

  set title(value: string) {
    this._title = value;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }
}
