export class AuthorizationData {

  id: number;
  fullName: string;
  login: string;
  password: string;
  email: string;

  constructor(fullName: string, login: string, password: string, email: string, id?: number) {
    this.fullName = fullName;
    this.login = login;
    this.password = password;
    this.email = email;
    this.id = id;
  }
}
