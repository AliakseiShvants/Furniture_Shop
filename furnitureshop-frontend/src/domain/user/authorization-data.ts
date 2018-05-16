export class AuthorizationData {

  fullName: string;
  login: string;
  password: string;
  email: string;

  constructor(login: string, password: string, fullName?: string, email?: string) {
    this.fullName = fullName;
    this.login = login;
    this.password = password;
    this.email = email;
  }
}
