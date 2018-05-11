package controller.shop;

import entity.Language;
import entity.UIResponse;
import entity.product.Category;
import entity.product.ManufacturerTranslate;
import entity.product.Product;
import entity.product.ProductTranslate;
import entity.shop.Storage;
import dto.shop.StorageDTO;
import exception.ProductExistsException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.LanguageService;
import service.product.*;
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

    @Autowired
    private LanguageService languageService;

    @Autowired
    private ProductTranslateService productTranslateService;

    @Autowired
    private ManufacturerTranslateService manufacturerTranslateService;

    private void makeLocalStorageList(Language language, List<StorageDTO> storageDTOList) {
        if (language.getId() > 1){
            storageDTOList.forEach(item -> {
                ManufacturerTranslate manufacturerTranslate = manufacturerTranslateService
                        .findByManufacturerId(item.getProduct().getManufacturer().getId());
                ProductTranslate productTranslate = productTranslateService.findByProductId(item.getProduct().getId());
                item.getProduct().setName(productTranslate.getName());
                item.getProduct().setDescription(productTranslate.getDescription());
                item.getProduct().getManufacturer().setTitle(manufacturerTranslate.getTitle());
            });
        }
    }

    /**
     * A method that returns a list of {@link StorageDTO} entities by product category.
     * @param category a title field of {@link Category} entity
     * @return a list of {@link StorageDTO} entities
     */
    @GetMapping("product/{category}/{lang}")
    public UIResponse<List<StorageDTO>> getProductsByCategory(@PathVariable String category,
                                                              @PathVariable String lang){
        Language language = languageService.findByName(lang);
        List<Product> products = productService.findProductsByCategory(category);
        if (products != null){
            List<StorageDTO> storageDTOList = storageService.findStorageListByCategory(category).stream()
                    .map(storageItem -> mapper.map(storageItem, StorageDTO.class))
                    .collect(Collectors.toList());

            storageDTOList.forEach(item -> item.getProduct().setUrl(
                    imageService.getProductImageUrl(item.getProduct().getId())
                    )
            );
            makeLocalStorageList(language, storageDTOList);
            return new UIResponse<>(true, storageDTOList);
        }
        return new UIResponse<>(new ProductExistsException());
    }

    /**
     * A method that returns a list of {@link StorageDTO} entities containing four entity of each category.
     * @return a list of {@link StorageDTO} entities
     */
    @GetMapping("product/cheapList/{lang}")
    public UIResponse<List<StorageDTO>> findCheapProducts(@PathVariable String lang){
        Language language = languageService.findByName(lang);
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
        makeLocalStorageList(language, storageDTOList);
        return new UIResponse<>(true, storageDTOList);
    }
}
