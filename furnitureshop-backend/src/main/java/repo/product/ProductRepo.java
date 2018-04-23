package repo.product;

import domain.product.Category;
import domain.product.Product;
import dto.product.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    /**
     * A method that returns a list of products added by a manager.
     * @param id a manager id
     * @return a list of products
     */
    @Query(value = "SELECT p.product_id, p.manufacturer_id, p.category_id, p.name, p.description\n" +
            "FROM furniture_shop.product p\n" +
            "JOIN furniture_shop.storage s\n" +
            "  ON p.product_id = s.product_id\n" +
            "WHERE s.manager_id = :id", nativeQuery = true)
    List<Product> findAllByManager_Id(@Param("id") Long id);

}
