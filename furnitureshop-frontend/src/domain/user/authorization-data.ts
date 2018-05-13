export class AuthorizationData {

  fullName: string;
  login: string;
  password: string;

  constructor(fullName: string, login: string, password: string) {
    this.fullName = fullName;
    this.login = login;
    this.password = password;
  }
}
