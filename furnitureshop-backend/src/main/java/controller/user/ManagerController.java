package controller.user;

import domain.UIResponse;
import domain.product.Manufacturer;
import domain.product.Product;
import domain.shop.Order;
import domain.shop.Status;
import dto.product.ProductDTO;
import dto.shop.OrderDTO;
import dto.shop.StorageItemDTO;
import exception.OrderExistsException;
import exception.UserNotFoundException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.product.CategoryService;
import service.product.ManufacturerService;
import service.product.ProductService;
import service.shop.*;
import service.user.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/manager/")
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

    @Autowired
    private CategoryService  categoryService;

    @Autowired
    private ManufacturerService manufacturerService;

    /**
     * A method that returns all manager's orders.
     * @param managerId a manager id
     * @return a list of orders
     */
    @GetMapping("{managerId}/order/all")
    public UIResponse<List<OrderDTO>> managerOrders(@PathVariable Long managerId){
        if (userService.isUserExists(managerId)){
            List<OrderDTO> orders = orderService.getManagerOrders(managerId).stream()
                    .map(order -> mapper.map(order, OrderDTO.class))
                    .collect(Collectors.toList());
            return new UIResponse<>(true, orders);
        }
        return new UIResponse<>(new UserNotFoundException());
    }

    /**
     * A method that deletes order
     * @param managerId a manager id
     * @param orderId a order id
     * @return true if order is deleted successfully
     */
    @DeleteMapping("{managerId}/order/{orderId}/delete")
    public UIResponse<Void> deleteOrder(@PathVariable Long managerId, @PathVariable Long orderId){
        if (userService.isUserExists(managerId)){
            if (orderService.isOrderExists(orderId)){
                orderService.delete(orderId);
                return new UIResponse<>(true);
            }
            return new UIResponse<>(new OrderExistsException());
        }
        return new UIResponse<>(new UserNotFoundException());
    }

    /**
     * A method that updates order
     * @param managerId a mamanger id. Manager is responsible for this order.
     * @param orderId order id
     * @param orderDTO order data transfer object
     * @return updated order entity
     */
    @PatchMapping("{managerId}/order/{orderId}/update")
    public UIResponse<OrderDTO> updateOrder(@PathVariable Long managerId, @PathVariable Long orderId,
                                            @RequestBody OrderDTO orderDTO) {
        if (userService.isUserExists(managerId)){
            if (orderService.isOrderExists(orderId)){
                Order updateOrder = orderService.getOrderById(orderId);
                Status status = statusService.getStatus(orderDTO.getStatus());
                updateOrder.setStatus(status);

                String date = orderDTO.getCompletionDate().split("[ T]")[0];
                String time = orderDTO.getCompletionDate().split("[ T]")[1];

                LocalDate finishDate = LocalDate.parse(date);
                LocalTime finishTime = LocalTime.parse(time);
                LocalDateTime finish = LocalDateTime.of(finishDate, finishTime);

                updateOrder.setCompletionDate(finish);

                updateOrder = orderService.update(updateOrder);

                return new UIResponse<>(true, mapper.map(updateOrder, OrderDTO.class));
            }
            return new UIResponse<>(new OrderExistsException());
        }
        return new UIResponse<>(new UserNotFoundException());
    }

    /**
     * A method that returns all product items added a manager.
     * @param managerId a manager id
     * @return list of product items
     */
    @GetMapping("{managerId}/product/all")
    public UIResponse<List<ProductDTO>> managerItems(@PathVariable Long managerId){
        if (userService.isUserExists(managerId)){
            List<ProductDTO> products = productService.getProductsByManagerId(managerId).stream()
                    .map(product -> mapper.map(product, ProductDTO.class))
                    .collect(Collectors.toList());
            return new UIResponse<>(true, products);
        }
        return new UIResponse<>(new UserNotFoundException());
    }

    /**
     * A method that adds a new product. A product entity knows about manager who added it
     * @param managerId a manager id
     * @param productDTO a product data transfer object
     * @return a new product DTO entity.
     */
    @PostMapping("{managerId}/product/add")
    public UIResponse<ProductDTO> addProduct(@PathVariable Long managerId, ProductDTO productDTO){
        if (userService.isUserExists(managerId)){
            Manufacturer manufacturer;
            if (manufacturerService.isManufacturerExists(productDTO.getManufacturer())){
                manufacturer = manufacturerService.getByTitle(productDTO.getManufacturer());
            } else {
                manufacturer = manufacturerService.addManufacturer(productDTO.getManufacturer());
            }

            Product newProduct = new Product(productDTO.getName(), productDTO.getCategory(), manufacturer,
                    productDTO.getDescription());
            newProduct = productService.addProduct(newProduct);
            return new UIResponse<>(true, mapper.map(newProduct, ProductDTO.class));
        }
        return new UIResponse<>(new UserNotFoundException());
    }

    /**
     * A method that returns list of storage items related by manager
     * @param managerId manager id
     * @return a list of storage items
     */
    @GetMapping("{managerId}/storage")
    public UIResponse<List<StorageItemDTO>> getManagerStorage(@PathVariable Long managerId){
        if (userService.isUserExists(managerId)){
            List<StorageItemDTO> storage = storageService.getStorageItemsByManager(managerId).stream()
                    .map(storageItem -> mapper.map(storageItem, StorageItemDTO.class))
                    .collect(Collectors.toList());
            return new UIResponse<>(true, storage);
        }
        return new UIResponse<>(new UserNotFoundException());
    }

}
