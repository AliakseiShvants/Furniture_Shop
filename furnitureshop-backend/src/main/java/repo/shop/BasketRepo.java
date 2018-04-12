package repo.shop;

import domain.shop.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BasketRepo extends JpaRepository<Basket, Long>{
}
