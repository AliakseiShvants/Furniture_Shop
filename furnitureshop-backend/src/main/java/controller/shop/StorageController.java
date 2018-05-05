package controller.shop;

import entity.UIResponse;
import entity.product.Category;
import entity.product.Product;
import entity.shop.Storage;
import dto.shop.StorageDTO;
import exception.ProductExistsException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.product.CategoryService;
import service.product.ImageService;
import service.product.ProductService;
import service.shop.StorageService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A controller class for {@link Storage} entity.
 */
@RestController
@RequestMapping("api/storage/")
public class StorageController {

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ImageService imageService;

    /**
     * A method that returns a list of {@link StorageDTO} entities by product category.
     * @param category a title field of {@link Category} entity
     * @return a list of {@link StorageDTO} entities
     */
    @GetMapping("product/{category}")
    public UIResponse<List<StorageDTO>> getProductsByCategory(@PathVariable String category){
        List<Product> products = productService.findProductsByCategory(category);
        if (products != null){
            List<StorageDTO> storageDTOList = storageService.findStorageListByCategory(category).stream()
                    .map(storageItem -> mapper.map(storageItem, StorageDTO.class))
                    .collect(Collectors.toList());

            storageDTOList.forEach(item -> item.getProduct().setUrl(
                    imageService.getProductImageUrl(item.getProduct().getId())
                    )
            );
            return new UIResponse<>(true, storageDTOList);
        }
        return new UIResponse<>(new ProductExistsException());
    }

    /**
     * A method that returns a list of {@link StorageDTO} entities containing four entity of each category.
     * @return a list of {@link StorageDTO} entities
     */
    @GetMapping("product/cheapList")
    public UIResponse<List<StorageDTO>> findCheapProducts(){
        List<Category> categoryList = categoryService.findAllCategories();
        List<Storage> storageList = new ArrayList<>();

        categoryList.forEach(category ->
                storageList.addAll(storageService.findStorageListByCategory(category.getTitle())
                        .stream()
                        .sorted(Comparator.comparing(Storage::getPrice))
                        .limit(4)
                        .collect(Collectors.toList())
                )
        );
        List<StorageDTO> storageDTOList = storageList.stream()
                .map(storage -> mapper.map(storage, StorageDTO.class))
                .collect(Collectors.toList());

        storageDTOList.forEach(item -> item.getProduct().setUrl(
                imageService.getProductImageUrl(item.getProduct().getId())
                )
        );
        return new UIResponse<>(true, storageDTOList);
    }
}
