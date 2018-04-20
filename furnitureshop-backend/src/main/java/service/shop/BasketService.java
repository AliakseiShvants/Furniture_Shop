package service.shop;

import domain.UIResponse;
import domain.shop.BasketItem;
import dto.shop.BasketItemDTO;
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


    public void addBasketItem(BasketItem basketItem) {
        basketRepo.save(basketItem);
    }

    public List<BasketItem> getBasketItemsByUserId(Long customerId) {
        return basketRepo.findAllByCustomer_Id(customerId);
    }

    public void deleteItemsByCustomerId(Long customerId) {
        basketRepo.deleteAllByCustomer_Id(customerId);
    }
}