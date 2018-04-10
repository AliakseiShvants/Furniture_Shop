package controller;

import domain.customer.Customer;
import domain.customer.Role;
import domain.http.HttpEntity;
import domain.shop.Requisite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CustomerService;
import service.RequisiteService;
import service.RoleService;

/**
 * <p>Controller class for {@link Customer} entity.
 */
@RestController
@RequestMapping("api/customer/")
public class CustomerController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RequisiteService requisiteService;

    @Autowired
    private CustomerService customerService;

    /**
     * A method for customer authorization.
     * @return
     */
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody String[] body){
        Customer customer = customerService.getCustomerByLoginAndPassword(body);
        return customer != null ? new ResponseEntity<>(customer, HttpStatus.ACCEPTED)
                : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     *
     * @param customer
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody Customer customer){
        Customer newCustomer;
        if (customerService.getCustomerByLoginAndPassword(customer.getLogin(), customer.getPassword()) == null){
            Role role = roleService.getRoleByTitle("ROLE_USER");
            newCustomer = new Customer(customer.getFullName(), customer.getLogin(), customer.getPassword(),
                    customer.getEmail(), role);
            customerService.addCustomer(newCustomer);
            return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * A method that provides a access to customer profile info.
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id){
        Customer customer = customerService.getCustomerById(id);
        return customer != null ? new ResponseEntity<>(customer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @param id
     * @param updateCustomer
     * @return
     */
    @PatchMapping("{id}/update")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody Customer updateCustomer){
        Customer customer = customerService.getCustomerById(id);
        if (customer != null){
            customer = customerService.updateCustomer(updateCustomer);
            return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * A method that returns a customer requisite.
     * @param id customer id
     * @return special entity
     */
    @GetMapping("{id}/requisite")
    public ResponseEntity<?> getRequisite(@PathVariable Long id){
        if (customerService.isCustomerExists(id)){
            Requisite requisite = requisiteService.getRequisiteByCustomerId(id);
            return new ResponseEntity<>(requisite, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("{id}/requisite/add")
    public ResponseEntity<?> addRequisite(@PathVariable Long id, @RequestBody Requisite requisite){
        Customer customer = customerService.getCustomerById(id);
        if (customer != null && customer.getRequisite() == null){
            Requisite newRequisite = new Requisite(requisite.getZip(), requisite.getCountry(), requisite.getCity(),
                    requisite.getAddress());
            Requisite req = requisiteService.addRequisite(newRequisite);
            customer.setRequisite(req);
            customerService.updateCustomer(customer);
            return new ResponseEntity<>(req, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("{customerId}/requisite/{requisiteId}/update")
    public ResponseEntity<?> updateRequisite(@PathVariable Long customerId, @PathVariable Long requisiteId,
                                             @RequestBody Requisite requisite){
        if (customerService.isCustomerExists(customerId)){
            if (requisiteService.isRequisiteExist(requisiteId)){
                Requisite updateRequisite = requisiteService.updateRequisite(requisite);
                return new ResponseEntity<>(updateRequisite, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        if (customerService.isCustomerExists(id)){
            customerService.deleteCustomerById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



}
