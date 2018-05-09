package repo.shop;

import entity.shop.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

    @Query(value = "SELECT od.order_item_id, od.order_id, od.product_id, od.quantity, od.total\n" +
            "FROM furniture_shop.order_item od\n" +
            "JOIN furniture_shop.orders o ON od.order_id = o.order_id\n" +
            "WHERE o.order_id = ?1", nativeQuery = true)
    List<OrderItem> findDetailsByOrderId(Long orderId);

}
