package repo.product;

import entity.product.Manufacturer;
import entity.product.ManufacturerTranslate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ManufacturerTranslateRepo extends JpaRepository<ManufacturerTranslate, Long> {

    ManufacturerTranslate findByManufacturerId(Long id);
}
