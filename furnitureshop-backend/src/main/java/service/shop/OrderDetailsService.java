package service.shop;

import domain.shop.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.shop.OrderDetailsRepo;

import java.util.List;

@Service
@Transactional
public class OrderDetailsService {
    @Autowired
    private OrderDetailsRepo detailsRepo;


    public List<OrderDetails> getDetailsByOrderId(Long orderId) {
        return detailsRepo.findDetailsByOrderId(orderId);
    }
}
