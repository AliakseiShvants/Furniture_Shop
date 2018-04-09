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

    public Customer getCustomerByLogAndPass(String[] body){
        return customerRepo.findCustomerByLoginAndPassword(body[0], body[1]);
    }

    public void saveCustomer(Customer newCustomer) {
        customerRepo.save(newCustomer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepo.getOne(id);
    }

    public Customer updateCustomer(Customer customer){
        return customerRepo.saveAndFlush(customer);
    }

    public Boolean isCustomerExistsById(Long id){
        return customerRepo.existsById(id);
    }


}
