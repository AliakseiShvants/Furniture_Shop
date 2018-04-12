package service.product;

import domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.product.ProductRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * <p>Service class for {@link Product} entity for working with repository interface
 */
@Service
@Transactional
public class ProductService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ProductRepo productRepo;

//    @SuppressWarnings("unchecked")
//    public List<Product> getAll() {
//        return em.createNativeQuery("SELECT p.product_id, p.manufacturer_id, p.category_id, p.name, " +
//                "p.description, pi.product_image_id, pi.url\n" +
//                "FROM  furniture_shop.product p\n" +
//                "  JOIN furniture_shop.product_image pi\n" +
//                "    ON p.product_id = pi.product_id", Product.class)
//                .getResultList();
//    }
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }
}
