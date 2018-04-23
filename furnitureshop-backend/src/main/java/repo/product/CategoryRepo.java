package repo.product;

import domain.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CategoryRepo extends JpaRepository<Category, Long> {

    Category findByTitle(String title);

    boolean existsByTitle(String title);
}
