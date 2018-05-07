package service.product;

import entity.product.Category;
import entity.product.Product;

import java.util.List;

/**
 * A service interface for {@link Product} entity for working with repository interface.
 */
public interface ProductService {

    /**
     * A method that returns all {@link Product} entities.
     * @return a list of {@link Product} entities
     */
    List<Product> findAllProducts();

    /**
     * A method that returns all {@link Product} entities with {@link Category} title equals @param.
     * @param title field of {@link Category} entity
     * @return a list of {@link Product} entities
     */
    List<Product> findProductsByCategory(String title);

    /**
     * A method that checks if this {@link Product} entity exists in database.
     * @param id field of {@link Product} entity
     * @return true if such {@link Product} entity exists and false otherwise
     */
    boolean isProductExists(Long id);

    Product findProductById(Long id);

    List<Product> findProductsByManagerId(Long id);

    Product addProduct(Product newProduct);

    Product update(Product updateProduct);
}
