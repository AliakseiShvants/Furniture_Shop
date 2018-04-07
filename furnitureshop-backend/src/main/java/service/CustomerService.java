package service;

import domain.customer.Customer;
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
}
