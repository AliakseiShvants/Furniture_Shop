package repo.product;

import entity.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CategoryRepo extends JpaRepository<Category, Long> {

    Category findByTitle(String title);

    boolean existsByTitle(String title);
}
