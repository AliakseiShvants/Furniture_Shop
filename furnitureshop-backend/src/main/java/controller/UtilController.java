package controller;

import domain.UIResponse;
import domain.shop.Status;
import domain.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.shop.StatusService;
import service.user.RoleService;

import java.util.List;

@RestController
@RequestMapping("api/util/")
public class UtilController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private StatusService statusService;

    @GetMapping("role/{role}")
    public UIResponse<Role> getRole(@PathVariable String role){
        return new UIResponse<>(true, roleService.getRoleByTitle(role));
    }

    @GetMapping("role/all")
    public UIResponse<List<Role>> getAllRoles(){
        return new UIResponse<>(true, roleService.getAll());
    }

    @GetMapping("status/all")
    public UIResponse<List<Status>> getAllStatus(){
        List<Status> statusList = statusService.getAll();
        return new UIResponse<>(true, statusList);
    }

}
