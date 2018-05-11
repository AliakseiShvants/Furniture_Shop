package service.impl;

import entity.product.ProductTranslate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.product.ProductTranslateRepo;
import service.product.ProductTranslateService;

@Service
public class ProductTranslateServiceImpl implements ProductTranslateService {

    @Autowired
    private ProductTranslateRepo productTranslateRepo;

    @Override
    public ProductTranslate findByProductId(Long id) {
        return productTranslateRepo.findProductTranslateByProductId(id);
    }
}
