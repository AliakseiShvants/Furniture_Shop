package service.product;

import domain.product.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.product.CategoryRepo;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category getByTitle(String title){
        return categoryRepo.findByTitle(title);
    }

    public List<Category> getAll(){
        return categoryRepo.findAll();
    }

    public boolean isExists(String title) {
        return categoryRepo.existsByTitle(title);
    }
}
