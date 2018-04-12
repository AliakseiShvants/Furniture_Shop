package repo.product;

import domain.product.Product;
import dto.product.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Access interface to {@link Product} entity in database
 */
@Repository
@Transactional
public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory_Title(String title);

    Product findByCategory_Title(String category);
}
