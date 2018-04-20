package controller.user;

import domain.UIResponse;
import domain.product.Product;
import dto.product.ProductDTO;
import dto.shop.StorageItemDTO;
import exception.UserNotFoundException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.product.ProductService;
import service.shop.*;
import service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("api/manager")
public class ManagerController {

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private RequisiteService requisiteService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private StatusService statusService;

    /**
     * A method that returns all product items added a manager.
     * @param id manager id
     * @return list of storage items
     */
    @PostMapping("{id}/items")
    public UIResponse<List<ProductDTO>> managerItems(@PathVariable Long id){
        if (userService.isUserExists(id)){
            List<Product> products = productService.getProductsByManagerId(id);
        }
        return new UIResponse<>(new UserNotFoundException());
    }
}
