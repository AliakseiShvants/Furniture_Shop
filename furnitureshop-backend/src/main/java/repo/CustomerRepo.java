package repo;

import domain.customer.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CustomerRepo extends CrudRepository<Customer, Long> {

    Customer findByLogin(String login);


}
