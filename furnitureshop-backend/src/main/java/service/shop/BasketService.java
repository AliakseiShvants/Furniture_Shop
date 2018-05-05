package service.shop;

import entity.shop.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.shop.BasketRepo;

import java.util.List;

@Service
@Transactional
public class BasketService {

    @Autowired
    private BasketRepo basketRepo;


    public void addBasketItem(Basket basketItem) {
        basketRepo.save(basketItem);
    }

    public List<Basket> getBasketItemsByUserId(Long customerId) {
        return basketRepo.findAllByCustomer_Id(customerId);
    }

    public void deleteItemsByCustomerId(Long customerId) {
        basketRepo.deleteAllByCustomer_Id(customerId);
    }

    public void deleteBasketItemByIds(Long customerId, Long productId){
        basketRepo.deleteBasketItemByCustomer_IdAndProduct_Id(customerId, productId);
    }
}
