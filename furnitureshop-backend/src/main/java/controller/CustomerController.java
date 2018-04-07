package controller;

import domain.customer.Customer;
import domain.customer.Role;
import domain.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repo.RoleRepo;
import service.RoleService;

import java.util.List;

/**
 * <p>Controller class for {@link Customer} entity.
 */
@RestController
@RequestMapping("/api/customers/")
public class CustomerController {

    @Autowired
    private RoleService roleService;

    @GetMapping("roles")
    public ResponseEntity<List<Role>> roles(){
        return new ResponseEntity<>(roleService.getAll());
    }

}
