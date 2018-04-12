package service.shop;

import domain.shop.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.shop.OrderRepo;

import java.util.List;

/**
 * <p>Service class for {@link Order} entity for working with repository interface
 */
@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public List<Order> getAll(){
        return orderRepo.findAll();
    }

    public List<Order> getCustomerOrders(Long id) {
        return orderRepo.findAllByCustomer_Id(id);
    }

    public Order getOrderById(Long id) {
        if (orderRepo.existsById(id)){
            return orderRepo.findById(id).get();
        }
        return null;
    }

    public boolean isOrderExists(Long orderId) {
        return orderRepo.existsById(orderId);
    }
}
