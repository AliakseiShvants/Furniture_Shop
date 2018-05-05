package service.impl;

import entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.product.ProductRepo;
import service.product.ProductService;

import java.util.List;

/**
 * A implementation of {@link ProductService} interface
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> findProductsByCategory(String title) {
        return productRepo.findAllByCategory_Title(title);
    }

    @Override
    public boolean isProductExists(Long id) {
        return productRepo.existsById(id);
    }

    @Override
    public Product findProductById(Long id) {
        if (productRepo.existsById(id)){
            return productRepo.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Product> findProductsByManagerId(Long id) {
        return productRepo.findAllByManager_Id(id);
    }

    @Override
    public Product addProduct(Product newProduct) {
        return productRepo.save(newProduct);
    }

    @Override
    public Product update(Product updateProduct) {
        return productRepo.saveAndFlush(updateProduct);
    }
}
