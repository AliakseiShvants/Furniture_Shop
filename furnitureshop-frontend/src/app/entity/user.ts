import {Role} from './role';

export class User {
  id: number;
  fullName: string;
  email: string;
  login: string;
  password: string;
  role: Role;

  constructor(_fullName: string, _email: string, _login: string, _pass: string, _role: Role, _id?: number) {
    this.id = _id;
    this.fullName = _fullName;
    this.email = _email;
    this.login = _login;
    this.password = _pass;
    this.role = _role;
  }
}
