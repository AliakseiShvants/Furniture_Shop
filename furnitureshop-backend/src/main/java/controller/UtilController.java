package controller;

import entity.UIResponse;
import entity.shop.Status;
import entity.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.shop.StatusService;
import service.user.RoleService;

import java.util.List;

/**
 * A controller for working with a {@link Role} and {@link Status} entities.
 */
@RestController
@RequestMapping("api/util/")
public class UtilController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private StatusService statusService;

    /**
     * A method that returns a {@link Role} by it title.
     * @param title a
     * @return
     */
    @GetMapping("role/{title}")
    public UIResponse<Role> getRole(@PathVariable String title){
        return new UIResponse<>(true, roleService.getRoleByTitle(title));
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
