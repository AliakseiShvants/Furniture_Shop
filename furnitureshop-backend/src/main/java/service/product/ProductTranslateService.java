package service.product;

import entity.product.ProductTranslate;

public interface ProductTranslateService {

    ProductTranslate findByProductId(Long id);
}
