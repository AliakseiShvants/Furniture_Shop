package repo.shop;

import entity.shop.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface StorageRepo extends JpaRepository<Storage, Long> {

    Storage findByProduct_Id(Long id);

    boolean existsByProduct_Id(Long id);

    @Query(value = "SELECT s.storage_id, s.product_id, s.code, s.price, s.quantity, s.manager_id\n" +
            "FROM furniture_shop.storage s\n" +
            "JOIN furniture_shop.product p\n" +
            "  ON s.product_id = p.product_id\n" +
            "JOIN furniture_shop.category c\n" +
            "  ON p.category_id = c.category_id\n" +
            "WHERE c.title LIKE :category", nativeQuery = true)
    List<Storage> findAllByCategory(@Param("category") String category);

    List<Storage> findAllByManager_Id(Long id);
}
