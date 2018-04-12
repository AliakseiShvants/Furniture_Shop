package repo.shop;

import domain.shop.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Access interface to {@link Order} entity in database
 */
@Repository
@Transactional
public interface OrderRepo extends JpaRepository<Order, Long> {

    @Query(value = "SELECT o.order_id, o.customer_id, o.manager_id, o.create_date, o.finish_date\n" +
            "FROM furniture_shop.orders o\n" +
            "WHERE customer_id = ?1", nativeQuery = true)
    List<Order> findAllByCustomerId(Long id);
}
