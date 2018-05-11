package service.impl;

import entity.product.ManufacturerTranslate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.product.ManufacturerTranslateRepo;
import service.product.ManufacturerTranslateService;

@Service
public class ManufacturerTranslateServiceImpl implements ManufacturerTranslateService {

    @Autowired
    private ManufacturerTranslateRepo manufacturerTranslateRepo;

    @Override
    public ManufacturerTranslate findByManufacturerId(Long id) {
        return manufacturerTranslateRepo.findByManufacturerId(id);
    }
}
