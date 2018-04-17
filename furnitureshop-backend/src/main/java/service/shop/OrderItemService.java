package service.shop;

import domain.shop.Order;
import domain.shop.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.shop.OrderItemRepo;

import java.util.List;

@Service
@Transactional
public class OrderItemService {
    @Autowired
    private OrderItemRepo orderItemRepo;

    public List<OrderItem> getDetailsByOrderId(Long orderId) {
        return orderItemRepo.findDetailsByOrderId(orderId);
    }

    public OrderItem addItem(OrderItem orderItem) {
        return orderItemRepo.save(orderItem);
    }
}
