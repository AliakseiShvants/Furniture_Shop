package controller.product;

import domain.UIResponse;
import domain.product.Image;
import domain.product.Product;
import domain.shop.StorageItem;
import dto.product.ImageDTO;
import dto.product.ProductDTO;
import exception.CategoryExistsException;
import exception.ProductExistsException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.product.ImageService;
import service.product.ProductService;
import service.shop.StorageService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/products/")
public class ProductController {

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private StorageService storageService;

    /**
     * A method that returns a list of products by category
     * @param category category title
     * @return list of products
     */
    @GetMapping("{category}")
    //todo nullpointer
    public UIResponse<List<ProductDTO>> getProductsByCategory(@PathVariable String category){
        if (productService.isCategoryExists(category)){
            List<Product> products = productService.getProductsByCategory(category);
            if (products != null){
                List<ProductDTO> productDTOList = products.stream()
                        .map(product -> mapper.map(product, ProductDTO.class))
                        .collect(Collectors.toList());

                for(ProductDTO productDTO : productDTOList){
                    List<String> imagesUrlList = imageService.getProductImagesUrl(productDTO.getId());
                    StorageItem storageItem = storageService.getStorageByProductId(productDTO.getId());

                    productDTO.setCode(storageItem.getCode());
                    productDTO.setPrice(storageItem.getPrice());
                    productDTO.setUrl((imagesUrlList.toArray(new String[0])));
                }
                return new UIResponse<>(true, productDTOList);
            }
            return new UIResponse<>(new ProductExistsException());
        }
        return new UIResponse<>(new CategoryExistsException());
    }

    /**
     * A method that returns all images of product
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


}
