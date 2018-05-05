package service.impl;

import entity.product.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.product.CategoryRepo;
import service.product.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category findCategoryByTitle(String title) {
        return categoryRepo.findByTitle(title);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }
}
