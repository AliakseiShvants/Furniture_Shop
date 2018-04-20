package service.product;

import domain.product.Product;
import dto.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.product.ProductRepo;

import java.util.List;

/**
 * <p>Service class for {@link Product} entity for working with repository interface
 */
@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public List<Product> getProductsByCategory(String title) {
        return productRepo.findAllByCategory_Title(title);
    }

    public boolean isCategoryExists(String category) {
        return productRepo.findAllByCategory_Title(category) != null;
    }

    public boolean isProductExists(Long id) {
        return productRepo.existsById(id);
    }

    public Product getProductById(Long id) {
        if (productRepo.existsById(id)){
            return productRepo.findById(id).get();
        }
        return null;
    }

    public List<Product> getProductsByManagerId(Long id) {
        return null;
    }
}
