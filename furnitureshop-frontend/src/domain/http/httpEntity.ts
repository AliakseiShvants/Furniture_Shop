export class HttpEntity {
  private _authorization: string[];
  private _body: any;
  private _success: boolean;


  get authorization(): string[] {
    return this._authorization;
  }

  set authorization(value: string[]) {
    this._authorization = value;
  }

  get body(): any {
    return this._body;
  }

  set body(value: any) {
    this._body = value;
  }

  get success(): boolean {
    return this._success;
  }

  set success(value: boolean) {
    this._success = value;
  }
}
