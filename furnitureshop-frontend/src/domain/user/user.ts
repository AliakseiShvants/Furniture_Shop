import {Requisite} from '../shop/requisite';

export class User {

  public id: number;
  public fullName: string;
  public login: string;
  public password: string;
  public email: string;
  public birthday: Date;
  public sex: boolean;
  public role: string;
  public requisite: Requisite;


  constructor(id?: number, fullName?: string, login?: string, password?: string, email?: string, birthday?: Date, sex?: boolean, role?: string) {
    this.id = id;
    this.fullName = fullName;
    this.login = login;
    this.password = password;
    this.email = email;
    this.birthday = birthday;
    this.sex = sex;
    this.role = role;
  }
}