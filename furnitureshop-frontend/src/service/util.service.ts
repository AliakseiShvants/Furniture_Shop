import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class UtilService {

  private base = 'api/util/';
  private role = 'role/';
  private status = 'status/';

  private all = 'all';

  constructor(private http: HttpClient) { }

  getRole(role: string) {
    return this.http.get(this.base + this.role + role);
  }

  getAllRoles() {
    return this.http.get(this.base + this.role + this.all);
  }

  getAllStatus() {
    return this.http.get(this.base + this.status + this.all);
  }
}
