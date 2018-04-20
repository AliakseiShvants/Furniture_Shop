export class AuthorizationData {

  public fullName: string;
  public login: string;
  public password: string;
  public email: string;

  constructor(fullName: string, login: string, password: string, email: string) {
    this.fullName = fullName;
    this.login = login;
    this.password = password;
    this.email = email;
  }
}
