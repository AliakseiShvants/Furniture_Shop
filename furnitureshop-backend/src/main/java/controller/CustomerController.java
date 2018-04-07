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

    @PostMapping("customer/login")
    public HttpEntity<Customer> login(@RequestBody HttpEntity<Object> entity){
        Customer customer = null;
        if (entity.isSuccess()){
            customer = customerService.getCustomerByLogAndPass(entity.getAuthorization());
        }
        return customer != null ? new HttpEntity<>(customer) : new HttpEntity<>(false);
    }

    @PostMapping("customer/register")
    public HttpEntity<Customer> register(@RequestBody HttpEntity<Customer> entity){
        Customer customer = null;
        if (entity.isSuccess()){
            customer = customerService.getCustomerByLogAndPass(entity.getAuthorization());
        }
        if (customer == null){
            Role role = roleService.getRoleByTitle("ROLE_USER");
            Customer newCustomer = new Customer(entity.getBody().getFullName(), entity.getBody().getLogin(),
                    entity.getBody().getPassword(), entity.getBody().getEmail(), role);
            customerService.saveCustomer(newCustomer);
            return new HttpEntity<>(newCustomer);
        } else {
            return new HttpEntity<>(false);
        }
    }

}
