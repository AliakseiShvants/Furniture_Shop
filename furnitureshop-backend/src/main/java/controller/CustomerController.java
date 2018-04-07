package controller;

import domain.customer.Customer;
import domain.customer.Role;
import domain.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CustomerService;
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

    @Autowired
    private CustomerService customerService;

    @PostMapping("customer/register")
    public HttpEntity<Customer> login(@RequestBody HttpEntity<String[]> entity){
        Customer customer = null;
        if (entity.isSuccess()){
            customer = customerService.getCustomerByLogAndPass(entity.getBody());
        }
        return customer != null ? new HttpEntity<>(customer) : new HttpEntity<>(false);
    }

    @GetMapping("roles")
    public HttpEntity<List<Role>> roles(){
        return new HttpEntity<>(roleService.getAll());
    }

}
