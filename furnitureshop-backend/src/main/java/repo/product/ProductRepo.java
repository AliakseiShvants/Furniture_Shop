package repo.product;

import domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Access interface to {@link Product} entity in database
 */
@Repository
@Transactional
public interface ProductRepo extends JpaRepository<Product, Long> {

}
