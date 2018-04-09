package controller;

import domain.customer.Customer;
import domain.customer.Role;
import domain.http.HttpEntity;
import domain.shop.Requisite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CustomerService;
import service.RequisiteService;
import service.RoleService;

import java.util.List;

/**
 * <p>Controller class for {@link Customer} entity.
 */
@RestController
@RequestMapping("/api/customer/")
public class CustomerController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RequisiteService requisiteService;

    @Autowired
    private CustomerService customerService;

    /**
     * A method for customer authorization.
     * @param entity
     * @return
     */
    @PostMapping("login")
    public HttpEntity<Customer> login(@RequestBody HttpEntity<Object> entity){
        Customer customer = null;
        if (entity.isSuccess()){
            customer = customerService.getCustomerByLogAndPass(entity.getAuthorization());
        }
        return customer != null ? new HttpEntity<>(customer) : new HttpEntity<>(false);
    }

    /**
     * A method for registration new customer.
     * @param entity
     * @return
     */
    @PostMapping("register")
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

    /**
     * A method that provides a access to customer profile info.
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public HttpEntity<Customer> editProfile(@PathVariable Long id){
        if (customerService.isCustomerExistsById(id)){
            return new HttpEntity<>(customerService.getCustomerById(id));
        } else {
            return new HttpEntity<>(false);
        }
    }

    /**
     * A method that updates customer profile info.
     * @param id
     * @param entity
     * @return
     */
    @PatchMapping("{id}/update")
    public HttpEntity<Customer> updateProfile(@PathVariable Long id, @RequestBody HttpEntity<Customer> entity){
        if (customerService.isCustomerExistsById(id)){
            return new HttpEntity<>(customerService.updateCustomer(entity.getBody()));
        } else {
            return new HttpEntity<>(false);
        }
    }

    /**
     * A method that returns a customer requisite.
     * @param id customer id
     * @return special entity
     */
    @PostMapping("{id}/requisite")
    public HttpEntity<Requisite> updateRequisite(@PathVariable Long id){
        if (customerService.isCustomerExistsById(id)){
            return new HttpEntity<>(requisiteService.getRequisiteByCustomerId(id));
        } else {
            return new HttpEntity<>(false);
        }
    }

}
