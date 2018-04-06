package controller;

import domain.customer.Customer;
import domain.customer.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repo.RoleRepo;

import java.util.List;

/**
 * <p>Controller class for {@link Customer} entity.
 */
@RestController
@RequestMapping("/api/customers/")
public class CustomerController {

    @Autowired
    private RoleRepo roleRepo;

    @GetMapping("roles")
    public List<Role> roles(){
        return (List<Role>) roleRepo.findAll();
    }

}
