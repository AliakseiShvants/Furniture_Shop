export class Image {

  private _id: number;
  private _url: string;

  constructor(url: string) {
    this._url = url;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get url(): string {
    return this._url;
  }

  set url(value: string) {
    this._url = value;
  }
}
