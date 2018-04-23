package controller.product;

import domain.UIResponse;
import domain.product.Category;
import domain.product.Manufacturer;
import domain.product.Product;
import domain.shop.Requisite;
import dto.product.ImageDTO;
import dto.product.ManufacturerDTO;
import dto.product.ProductDTO;
import dto.shop.RequisiteDTO;
import dto.shop.StorageItemDTO;
import exception.CategoryExistsException;
import exception.ProductExistsException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.product.CategoryService;
import service.product.ImageService;
import service.product.ManufacturerService;
import service.product.ProductService;
import service.shop.RequisiteService;
import service.shop.StorageService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/product/")
public class ProductController {

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private RequisiteService requisiteService;

    /**
     * A method that returns a list of products by category
     * @param category category title
     * @return list of products
     */
    @GetMapping("{category}")
    public UIResponse<List<StorageItemDTO>> getProductsByCategory(@PathVariable String category){
        if (categoryService.isExists(category)){

            List<Product> products = productService.getProductsByCategory(category);
            if (products != null){
                List<StorageItemDTO> storageItemDTOS = storageService.getStorageItemsByCategory(category).stream()
                        .map(storageItem -> mapper.map(storageItem, StorageItemDTO.class))
                        .collect(Collectors.toList());

                storageItemDTOS.forEach(item -> item.getProduct().setUrl(
                                imageService.getProductImageUrl(item.getProduct().getId())
                                )
                );
                return new UIResponse<>(true, storageItemDTOS);
            }
            return new UIResponse<>(new ProductExistsException());
        }
        return new UIResponse<>(new CategoryExistsException());
    }

    /**
     * A method that returns all image entities of product
     * @param id product id
     * @return list of images
     */
    @GetMapping("{id}/images")
    public UIResponse<List<ImageDTO>> getProductImages(@PathVariable Long id){
        if (productService.isProductExists(id)){
            List<ImageDTO> imageDTOList = imageService.getProductImages(id).stream()
                    .map(image -> mapper.map(image, ImageDTO.class))
                    .collect(Collectors.toList());
            return new UIResponse<>(true, imageDTOList);
        }
        return new UIResponse<>(new ProductExistsException());
    }

    /**
     * A method that returns a category entity by it title.
     * @param title category title
     * @return a category
     */
    @GetMapping("category/{title}")
    public UIResponse<Category> getCategory(@PathVariable String title){
        Category category = categoryService.getByTitle(title);
        return new UIResponse<>(true, category);
    }

    /**
     * A method that returns a list of all category entities.
     * @return a list of categories
     */
    @GetMapping("categories")
    public UIResponse<List<Category>> getCategories(){
        List<Category> categories = categoryService.getAll();
        return new UIResponse<>(true, categories);
    }

    /**
     *
     * @param productId
     * @param manufacturerDTO
     * @return
     */
    @PostMapping("{productId}/manufacturer/add")
    public UIResponse<ManufacturerDTO> addManufacturer(@PathVariable Long productId,
                                                       @RequestBody ManufacturerDTO manufacturerDTO){
        if (productService.isProductExists(productId)){
            RequisiteDTO requisiteDTO = manufacturerDTO.getRequisite();
            Requisite newRequisite = new Requisite(requisiteDTO.getZip(), requisiteDTO.getCountry(),
                    requisiteDTO.getCity(), requisiteDTO.getAddress());
            newRequisite = requisiteService.addRequisite(newRequisite);
            Manufacturer newManufacturer = new Manufacturer(manufacturerDTO.getTitle(), newRequisite);
            newManufacturer = manufacturerService.addManufacturer(newManufacturer);
            return new UIResponse<>(true, mapper.map(newManufacturer, ManufacturerDTO.class));
        }
        return new UIResponse<>(new ProductExistsException());
    }

    @GetMapping("manufacturer/all")
    public UIResponse<List<Manufacturer>> getAllManufacturers(){
        List<Manufacturer> manufacturerList = manufacturerService.getAll();
        return new UIResponse<>(true, manufacturerList);
    }

}
