package controller.product;

import entity.UIResponse;
import entity.product.Category;
import entity.product.Image;
import entity.product.Manufacturer;
import entity.product.Product;
import entity.shop.Requisite;
import dto.product.ImageDTO;
import dto.product.ManufacturerDTO;
import dto.shop.RequisiteDTO;
import exception.ProductExistsException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.product.CategoryService;
import service.product.ImageService;
import service.product.ManufacturerService;
import service.product.ProductService;
import service.shop.RequisiteService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Controller class for {@link Product} entity.
 */
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
    private ManufacturerService manufacturerService;

    @Autowired
    private RequisiteService requisiteService;

    /**
     * A method that returns all {@link Image} entities that owned by {@link Product} entity.
     * @param id  id field of {@link Product} entity
     * @return list of {@link Image} entities
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
        Category category = categoryService.findCategoryByTitle(title);
        return new UIResponse<>(true, category);
    }

    /**
     * A method that returns a list of all category entities.
     * @return a list of categories
     */
    @GetMapping("categories")
    public UIResponse<List<Category>> getCategories(){
        List<Category> categories = categoryService.findAllCategories();
        return new UIResponse<>(true, categories);
    }

    /**
     * A method that adds new manufacturer to product.
     * @param productId product id
     * @param manufacturerDTO a manufacturer dto object
     * @return new manufacturer dto object
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

    /**
     * A method that returns all manufacturers.
     * @return list of manufacturers
     */
    @GetMapping("manufacturer/all")
    public UIResponse<List<Manufacturer>> getAllManufacturers(){
        List<Manufacturer> manufacturerList = manufacturerService.getAll();
        return new UIResponse<>(true, manufacturerList);
    }

}
