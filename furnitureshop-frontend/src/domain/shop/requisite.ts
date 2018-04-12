export class Requisite {

  private _id: number;
  private _zip: string;
  private _country: string;
  private _city: string;
  private _address: string;


  constructor(zip: string, country: string, city: string, address: string) {
    this._zip = zip;
    this._country = country;
    this._city = city;
    this._address = address;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get zip(): string {
    return this._zip;
  }

  set zip(value: string) {
    this._zip = value;
  }

  get country(): string {
    return this._country;
  }

  set country(value: string) {
    this._country = value;
  }

  get city(): string {
    return this._city;
  }

  set city(value: string) {
    this._city = value;
  }

  get address(): string {
    return this._address;
  }

  set address(value: string) {
    this._address = value;
  }
}
