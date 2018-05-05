package repo.shop;

import entity.shop.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Access interface to {@link Order} entity in database
 */
@Repository
@Transactional
public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findAllByCustomer_Id(Long id);

    List<Order> findAllByManager_Id(Long id);

    void deleteById(Long id);
}
