package controller.user;

import dto.shop.*;
import entity.Language;
import entity.UIResponse;
import entity.product.ManufacturerTranslate;
import entity.product.Product;
import entity.product.ProductTranslate;
import entity.shop.*;
import entity.user.User;
import dto.user.UserDTO;
import entity.user.UserTranslate;
import exception.*;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.LanguageService;
import service.product.ManufacturerTranslateService;
import service.product.ProductService;
import service.product.ProductTranslateService;
import service.product.UserTranslateService;
import service.shop.*;
import service.user.UserService;
import entity.Localization;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Controller class for {@link User} entity with 'ROLE_USER' role.
 */
@RestController
@RequestMapping("api/customer/")
public class CustomerController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

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
    private LanguageService languageService;

    @Autowired
    private ProductTranslateService productTranslateService;

    @Autowired
    private ManufacturerTranslateService manufacturerTranslateService;

    @Autowired
    private UserTranslateService userTranslateService;

    private List<? extends ShopDTO> makeLocalList(Language language, List<? extends ShopDTO> list) {
        if (language.getId() > 1){
            list.forEach(item -> {
                ManufacturerTranslate manufacturerTranslate = manufacturerTranslateService
                        .findByManufacturerId(item.getProduct().getManufacturer().getId());
                ProductTranslate productTranslate = productTranslateService.findByProductId(item.getProduct().getId());
                item.getProduct().setName(productTranslate.getName());
                item.getProduct().setDescription(productTranslate.getDescription());
                item.getProduct().getManufacturer().setTitle(manufacturerTranslate.getTitle());
            });
        }
        return list;
    }

    /**
     * PROFILE METHODS
     */

    /**
     * A method that provides a access to user's profile info.
     * @param id user id
     * @return user entity
     */
    @GetMapping("{id}")
    public UIResponse<UserDTO> getUser(@PathVariable Long id){
        User user = userService.findUserById(id);
        return user != null ? new UIResponse<>(true, mapper.map(user, UserDTO.class))
                : new UIResponse<>(new UserNotFoundException());
    }

    /**
     * A method for updating user's profile info
     * @param updateUser user entity with updated fields
     * @return user entity
     */
    @PatchMapping("update")
    public UIResponse<UserDTO> updateProfile(@RequestBody UserDTO updateUser){
        if (userService.isUserExists(updateUser.getId())){
            User dbUser = userService.findUserById(updateUser.getId());
            dbUser.setFullName(updateUser.getFullName());
            dbUser.setEmail(updateUser.getEmail());
            dbUser.setBirthday(updateUser.getBirthday());
            dbUser.setSex(updateUser.getSex());
            dbUser = userService.updateUser(dbUser);

            return new UIResponse<>(true, mapper.map(dbUser, UserDTO.class));
        }
        return new UIResponse<>(new UserNotFoundException());
    }

    /**
     * A method that deletes a {@link User} entity by it id
     * @param id field of {@link User} entity
     * @return true if a {@link User} entity is deleted successfully
     */
    @DeleteMapping("{id}/delete")
    public UIResponse<Void> deleteUser(@PathVariable Long id){
        if (userService.isUserExists(id)){
            userService.deleteUserById(id);
            return new UIResponse<>(true);
        }
        return new UIResponse<>(new UserNotFoundException());
    }

    /**
     * REQUISITE
     */

    /**
     * A method that returns the user's requisite.
     * @param id user id
     * @return user requisite
     */
    @GetMapping("{id}/requisite")
    public UIResponse<RequisiteDTO> getRequisite(@PathVariable Long id){
        if (userService.isUserExists(id)){
            Requisite requisite = requisiteService.getRequisiteByUserId(id);
            return new UIResponse<>(true, mapper.map(requisite, RequisiteDTO.class));
        }
        return new UIResponse<>(new UserNotFoundException());
    }

    /**
     * A method for adding user's requisite
     * @param id user id
     * @param requisite requisite entity
     * @return new requisite entity binding with concrete user
     */
    @PostMapping("{id}/requisite/add")
    public UIResponse<RequisiteDTO> addRequisite(@PathVariable Long id,
                                                 @RequestBody RequisiteDTO requisite){
        User user = userService.findUserById(id);
        if (user != null){
            if (user.getRequisite() == null){
                Requisite newRequisite = new Requisite(requisite.getZip(), requisite.getCountry(), requisite.getCity(),
                        requisite.getAddress());
                newRequisite = requisiteService.addRequisite(newRequisite);
                user.setRequisite(newRequisite);
                userService.updateUser(user);
                return new UIResponse<>(true, mapper.map(newRequisite, RequisiteDTO.class));
            }
            return new UIResponse<>(new RequisiteExistsException());
        }
        return new UIResponse<>(new UserNotFoundException());
    }

    /**
     * A method that update requisite of specified user
     * @param customerId
     * @param requisiteDTO
     * @return
     */
    @PatchMapping("{customerId}/requisite/update")
    public UIResponse<RequisiteDTO> updateRequisite(@PathVariable Long customerId,
                                                    @RequestBody RequisiteDTO requisiteDTO){
        if (userService.isUserExists(customerId)){
            if (requisiteService.isRequisiteExist(requisiteDTO.getId())){
                Requisite requisite = new Requisite(requisiteDTO.getId(), requisiteDTO.getZip(),
                        requisiteDTO.getCountry(), requisiteDTO.getCity(), requisiteDTO.getAddress());
                requisite = requisiteService.updateRequisite(requisite);
                return new UIResponse<>(true,  mapper.map(requisite, RequisiteDTO.class));
            }
            return new UIResponse<>(new RequisiteNotFoundException());
        }
        return new UIResponse<>(new UserNotFoundException());
    }

    /**
     * ORDERS METHODS
     */

    /**
     * A method that gives all customer's orders
     * @param id a user id
     * @return list of orders
     */
    @GetMapping("{id}/order/all/{lang}")
    public UIResponse<List<OrderDTO>> getAllUserOrders(@PathVariable Long id,
                                                       @PathVariable String lang){
        List<OrderDTO> userOrdersDto = orderService.getCustomerOrders(id).stream()
                .map(order -> mapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
        Language language = languageService.findByName(lang);
        if (language.getId() > 1){
            userOrdersDto.forEach(item -> {
                User manager = userService.findByFullName(item.getManager());
                UserTranslate managerTranslate = userTranslateService.findByUserId(manager.getId());
                item.setManager(managerTranslate.getFullName());
            });
        }
        return new UIResponse<>(true, userOrdersDto);
    }

    /**
     * A method that represents details of customer's order
     * @param customerId
     * @param orderId
     * @return
     */
    @GetMapping("{customerId}/order/{orderId}")
    public UIResponse<List<OrderItemDTO>> getOrderInfo(@PathVariable Long customerId,
                                                       @PathVariable Long orderId){
        if (userService.isUserExists(customerId)){
            if (orderService.isOrderExists(orderId)){

                List<OrderItemDTO> detailsList = orderItemService.getDetailsByOrderId(orderId).stream()
                        .map(details -> mapper.map(details, OrderItemDTO.class))
                        .collect(Collectors.toList());

                for (OrderItemDTO orderItemDTO : detailsList){
                    Storage storage = storageService.getStorageItemByProductId(orderItemDTO.getProduct().getId());
                    orderItemDTO.setCode(storage.getCode());
                }
                return new UIResponse<>(true, detailsList);
            }
            return new UIResponse<>(new OrderExistsException());
        }
        return new UIResponse<>(new UserNotFoundException());
    }

    /**
     * BASKET
     */

    /**
     * A method that adds a one product item to basket
     * @param customerId customer id
     * @param productId product id
     * @return success message or appropriate exception otherwise
     */
    @GetMapping("{customerId}/basket/{productId}/add")
    public UIResponse<Void> addProductToBasket(@PathVariable Long customerId,
                                               @PathVariable Long productId){
        if (storageService.isItemExists(productId) && storageService.isItemAvailable(productId, 1)){
            User customer = userService.findUserById(customerId);
            Product product = productService.findProductById(productId);
            Basket basketItem = new Basket(customer, product, 1);
            basketService.addBasketItem(basketItem);
            return new UIResponse<>(true);
        }
        return new UIResponse<>(new StorageNotFoundException());
    }

    @DeleteMapping("{customerId}/basket/{productId}/delete")
    public UIResponse<Void> deleteBasketItem(@PathVariable Long customerId,
                                             @PathVariable Long productId){
        if (userService.isUserExists(customerId)){
            basketService.deleteBasketItemByIds(customerId, productId);
            return new UIResponse<>(true);
        }
        return new UIResponse<>(new UserExistsException());
    }

    /**
     * A method that returns all products in customer's basket.
     * @param customerId customer id
     * @return a list of products in the basket.
     */
    @GetMapping("{customerId}/basket/all/{lang}")
    public UIResponse<List<? extends ShopDTO>> getAllBasketItems(@PathVariable Long customerId,
                                                                 @PathVariable String lang){
        Language language = languageService.findByName(lang);
        List<? extends ShopDTO> basketItemDTOS = basketService.getBasketItemsByUserId(customerId).stream()
                .map(basketItem -> mapper.map(basketItem, BasketDTO.class))
                .peek(
                        basketItemDTO -> basketItemDTO.setPrice(
                                storageService.getStorageItemByProductId(
                                        basketItemDTO.getProduct().getId()
                                ).getPrice()
                        )
                )
                .collect(Collectors.toList());
        basketItemDTOS = makeLocalList(language, basketItemDTOS);
        return new UIResponse<>(true, basketItemDTOS);
    }

    @PostMapping("{customerId}/basket/book")
    public UIResponse<Void> makeOrder(@PathVariable Long customerId, @RequestBody List<Long> idList){
        List<Basket> basketList = new ArrayList<>();
        idList.forEach(item -> basketList.add(basketService.findById(item)));

        User customer = userService.findUserById(customerId);
        User manager = userService.getFreeManager();
        Status status = statusService.findById(1L);
        Order newOrder = orderService.addOrder(new Order(customer, manager, LocalDateTime.now(), status));

        for(Basket basket : basketList){
            Double price = storageService.findPriceByProductId(basket.getProduct().getId());
            OrderItem orderItem = new OrderItem(newOrder, basket.getProduct(), basket.getQuantity(),
                    price * basket.getQuantity());
            orderItemService.addItem(orderItem);
        }

        basketService.deleteItemsByCustomerId(customerId);
        return new UIResponse<>(true);
    }

}
