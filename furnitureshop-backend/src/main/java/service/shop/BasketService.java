package service.shop;

import entity.shop.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.shop.BasketRepo;

import java.util.List;

@Service
public class BasketService {

    @Autowired
    private BasketRepo basketRepo;


    @Transactional
    public void addBasketItem(Basket basketItem) {
        basketRepo.save(basketItem);
    }

    @Transactional
    public List<Basket> getBasketItemsByUserId(Long customerId) {
        return basketRepo.findAllByCustomer_Id(customerId);
    }

    public void deleteItemsByCustomerId(Long customerId) {
        basketRepo.deleteAllByCustomer_Id(customerId);
    }

    public void deleteBasketItemByIds(Long customerId, Long productId){
        basketRepo.deleteBasketItemByCustomer_IdAndProduct_Id(customerId, productId);
    }

    public Basket findById(Long id) {
        return basketRepo.findById(id).get();
    }
}
