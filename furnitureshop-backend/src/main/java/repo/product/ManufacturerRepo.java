package repo.product;

import entity.product.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ManufacturerRepo extends JpaRepository<Manufacturer, Long> {

    Manufacturer findByTitleAndRequisite_Country(String title, String country);

    boolean existsByTitleAndRequisite_Country(String title, String country);
}
