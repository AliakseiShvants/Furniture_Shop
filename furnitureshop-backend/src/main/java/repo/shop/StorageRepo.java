package repo.shop;

import domain.shop.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StorageRepo extends JpaRepository<Storage, Long> {

    Storage findByProduct_Id(Long id);
}
