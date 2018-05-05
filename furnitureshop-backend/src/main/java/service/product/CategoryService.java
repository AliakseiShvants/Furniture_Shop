package service.product;

import entity.product.Category;

import java.util.List;

/**
 * A service interface for {@link Category} entity for working with repository interface.
 */
public interface CategoryService {

    /**
     * A method that returns a {@link Category} entity with title equals @param.
     * @param title a title field of {@link Category} entity
     * @return a {@link Category} entity
     */
    Category findCategoryByTitle(String title);

    /**
     * A method that returns all {@link Category} entities.
     * @return a list of {@link Category} entities
     */
    List<Category> findAllCategories();
}
