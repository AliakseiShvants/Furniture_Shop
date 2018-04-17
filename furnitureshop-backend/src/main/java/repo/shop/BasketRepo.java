package repo.shop;

import domain.shop.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BasketRepo extends JpaRepository<BasketItem, Long>{

    List<BasketItem> findAllByCustomer_Id(Long id);

    void deleteAllByCustomer_Id(Long id);
}
