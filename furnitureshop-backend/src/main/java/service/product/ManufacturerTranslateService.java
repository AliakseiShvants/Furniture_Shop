package service.product;

import entity.product.ManufacturerTranslate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.product.ManufacturerRepo;

public interface ManufacturerTranslateService {

    ManufacturerTranslate findByManufacturerId(Long id);
}
