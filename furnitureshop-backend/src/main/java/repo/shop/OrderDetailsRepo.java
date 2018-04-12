package repo.shop;

import domain.shop.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Long> {

    @Query(value = "SELECT od.order_details_id, od.order_id, od.product_id, od.quantity\n" +
            "FROM furniture_shop.order_details od\n" +
            "JOIN furniture_shop.orders o ON od.order_id = o.order_id\n" +
            "WHERE o.order_id = ?1", nativeQuery = true)
    List<OrderDetails> findDetailsByOrderId(Long orderId);
}
