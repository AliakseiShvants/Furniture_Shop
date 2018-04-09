package repo;

import domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Customer findCustomerByLogin(String login);

    Customer findCustomerByLoginAndPassword(String login, String password);



}
