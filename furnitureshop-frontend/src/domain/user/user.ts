import {Role} from './role';

export class User {

  private _id: number;
  private _fullName: string;
  private _login: string;
  private _password: string;
  private _email: string;
  private _birthday: string;
  private _sex: boolean;
  private _role: Role;


  constructor(fullName: string, login?: string, password?: string, email?: string, birthday?: string, sex?: boolean, role?: Role) {
    this._fullName = fullName;
    this._login = login;
    this._password = password;
    this._email = email;
    this._birthday = birthday;
    this._sex = sex;
    this._role = role;
  }


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get fullName(): string {
    return this._fullName;
  }

  set fullName(value: string) {
    this._fullName = value;
  }

  get login(): string {
    return this._login;
  }

  set login(value: string) {
    this._login = value;
  }

  get password(): string {
    return this._password;
  }

  set password(value: string) {
    this._password = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get birthday(): string {
    return this._birthday;
  }

  set birthday(value: string) {
    this._birthday = value;
  }

  get sex(): boolean {
    return this._sex;
  }

  set sex(value: boolean) {
    this._sex = value;
  }

  get role(): Role {
    return this._role;
  }

  set role(value: Role) {
    this._role = value;
  }
}
