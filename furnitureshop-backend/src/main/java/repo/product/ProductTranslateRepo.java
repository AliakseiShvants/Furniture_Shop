package repo.product;

import entity.product.ProductTranslate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTranslateRepo extends JpaRepository<ProductTranslate, Long> {

    ProductTranslate findProductTranslateByProductId(Long id);
}
