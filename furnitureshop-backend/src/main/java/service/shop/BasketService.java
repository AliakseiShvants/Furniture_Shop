package service.shop;

import domain.shop.BasketItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.shop.BasketRepo;

@Service
@Transactional
public class BasketService {

    @Autowired
    private BasketRepo basketRepo;


    public void addBasketItem(BasketItem basketItem) {
        basketRepo.save(basketItem);
    }
}
