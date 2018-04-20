export class Requisite {

  public id: number;
  public zip: string;
  public country: string;
  public city: string;
  public address: string;


  constructor(zip: string, country: string, city: string, address: string) {
    this.zip = zip;
    this.country = country;
    this.city = city;
    this.address = address;
  }
}
