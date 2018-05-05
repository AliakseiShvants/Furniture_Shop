package repo.shop;

import entity.shop.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BasketRepo extends JpaRepository<Basket, Long>{

    List<Basket> findAllByCustomer_Id(Long id);

    void deleteAllByCustomer_Id(Long id);

    void deleteBasketItemByCustomer_IdAndProduct_Id(Long customerId, Long productId);

}
