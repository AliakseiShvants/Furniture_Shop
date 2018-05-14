import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {User} from '../../domain/user/user';
import {Role} from '../../domain/user/role';
import {AuthorizationData} from '../../domain/user/authorization-data';
import {Uiresponse} from '../../domain/uiresponse';
import {Requisite} from '../../domain/shop/requisite';
import {UtilService} from '../../service/util.service';
import {AdminService} from '../../service/admin.service';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent implements OnInit {

  fullName: string;
  login: string;
  password: string;
  email: string;

  managers: User[];
  tempManager = new User();
  role: Role;
  roleList: Role[];
  success = false;
  isDeleted = false;
  MANAGER = 'ROLE_MANAGER';

  constructor(private adminService: AdminService,
              private utilService: UtilService,
              private cd: ChangeDetectorRef) {

    this.tempManager.requisite = new Requisite();
  }

  ngOnInit() {
    this.loadRole(this.MANAGER);
    this.loadAllManagers();
  }

  private loadRole(role: string) {
    this.utilService.getRole(role).subscribe(
      (res: Uiresponse) => {
        this.role = res.body;
      }
    )
  }

  private loadAllManagers() {
    this.adminService.getAllManagers()
      .subscribe(
        (data: Uiresponse) => {
          this.success = data.success;
          this.managers = data.body;
        }
      );
  }

  addNewManager() {
    this.adminService.addNewManager(new AuthorizationData(this.fullName, this.login, this.password))
      .subscribe(
        (data: Uiresponse) => {
          this.success = data.success;
        }
      );
  }

  prepareEditManager(manager: User){
    this.tempManager = manager;
  }

  updateManager(manager: User){
    this.adminService.updateUser(manager)
      .subscribe(
        (res: Uiresponse) => {
          if (res.success){
            this.loadAllManagers();
            this.cd.detectChanges();
          }
        }
      )
  }

  prepareDeleteManager(manager: User){
    this.tempManager = manager;
  }

  deleteManager(managerId: number){
    this.adminService.deleteCustomer(managerId)
      .subscribe(
        (res: Uiresponse) => {
          this.isDeleted = res.success;
          if (this.isDeleted){
            this.loadAllManagers();
            this.cd.detectChanges();
          }
        }
      )
  }

  getSex(user: User){
    if (user.sex){
      return 'checked';
    }
    return '';
  }

  selected(first: any, second: any){
    if (first === second){
      return 'selected';
    }
  }

  close(){
    this.success = false;
  }

}
