package repo.shop;

import domain.shop.StorageItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StorageRepo extends JpaRepository<StorageItem, Long> {

    StorageItem findByProduct_Id(Long id);

    boolean existsByProduct_Id(Long id);
}
