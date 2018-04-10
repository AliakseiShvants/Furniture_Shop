package service;

import domain.customer.Customer;
import domain.shop.Requisite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.CustomerRepo;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public Customer getCustomerByLogin(String login){
        return customerRepo.findCustomerByLogin(login);
    }

    public void addCustomer(Customer newCustomer) {
        customerRepo.save(newCustomer);
    }

    public Customer getCustomerById(Long id) {
        if (customerRepo.findById(id).isPresent()){
            return customerRepo.findById(id).get();
        }
        return null;
    }

    public Customer updateCustomer(Customer customer){
        return customerRepo.saveAndFlush(customer);
    }

    public Boolean isCustomerExists(Long id){
        return customerRepo.existsById(id);
    }

    public void deleteCustomerById(Long id) {
        customerRepo.deleteById(id);
    }

    public Customer getCustomerByLoginAndPassword(String login, String password) {
        return customerRepo.findCustomerByLoginAndPassword(login, password);
    }

    public Customer getCustomerByLoginAndPassword(String[] body) {
        return customerRepo.findCustomerByLoginAndPassword(body[0], body[1]);
    }

}
