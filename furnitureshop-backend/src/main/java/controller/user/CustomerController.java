package controller.user;

import entity.UIResponse;
import entity.product.Product;
import entity.shop.*;
import entity.user.User;
import dto.shop.BasketDTO;
import dto.shop.OrderDTO;
import dto.shop.OrderItemDTO;
import dto.shop.RequisiteDTO;
import dto.user.UserDTO;
import exception.*;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.product.ProductService;
import service.shop.*;
import service.user.UserService;

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
    public UIResponse<RequisiteDTO> addRequisite(@PathVariable Long id, @RequestBody RequisiteDTO requisite){
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
     * A method that deletes a user
     * @param id user id
     * @return http status
     */
    @DeleteMapping("{id}/delete")
    public UIResponse<Void> deleteUser(@PathVariable Long id){
        if (userService.isUserExists(id)){
            userService.deleteCustomerById(id);
            return new UIResponse<>(true);
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
    @GetMapping("{id}/order/all")
    public UIResponse<List<OrderDTO>> getAllUserOrders(@PathVariable Long id){
        if (userService.isUserExists(id)){
            List<OrderDTO> userOrdersDto = orderService.getCustomerOrders(id).stream()
                    .map(order -> mapper.map(order, OrderDTO.class))
                    .collect(Collectors.toList());
            return new UIResponse<>(true, userOrdersDto);
        }
        return new UIResponse<>(new UserNotFoundException());
    }

    /**
     * A method that represents details of customer's order
     * @param customerId
     * @param orderId
     * @return
     */
    @GetMapping("{customerId}/order/{orderId}")
    public UIResponse<List<OrderItemDTO>> getOrderInfo(@PathVariable Long customerId, @PathVariable Long orderId){
        if (userService.isUserExists(customerId)){
            if (orderService.isOrderExists(orderId)){

                List<OrderItemDTO> detailsList = orderItemService.getDetailsByOrderId(orderId).stream()
                        .map(details -> mapper.map(details, OrderItemDTO.class))
                        .collect(Collectors.toList());

                for (OrderItemDTO orderItemDTO : detailsList){
                    Storage storage = storageService.getStorageItemByProductId(orderItemDTO.getProduct().getId());
                    orderItemDTO.setPrice(storage.getPrice());
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
    public UIResponse<Void> addProductToBasket(@PathVariable Long customerId, @PathVariable Long productId){
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
    public UIResponse<Void> deleteBasketItem(@PathVariable Long customerId, @PathVariable Long productId){
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
    @GetMapping("{customerId}/basket/all")
    public UIResponse<List<BasketDTO>> getAllBasketItems(@PathVariable Long customerId){
        List<BasketDTO> basketItemDTOS = basketService.getBasketItemsByUserId(customerId).stream()
                .map(basketItem -> mapper.map(basketItem, BasketDTO.class))
                .peek(
                        basketItemDTO -> basketItemDTO.setPrice(
                                storageService.getStorageItemByProductId(
                                        basketItemDTO.getProduct().getId()
                                ).getPrice()
                        )
                )
                .collect(Collectors.toList());
        return new UIResponse<>(true, basketItemDTOS);
    }

    @PostMapping("{customerId}/basket/book")
    public UIResponse<Void> makeOrder(@PathVariable Long customerId, @RequestBody List<BasketDTO> basketItemsDtos){
        if (userService.isUserExists(customerId)){
            List<Basket> basketList = basketItemsDtos.stream()
                    .map(basketItemDTO -> mapper.map(basketItemDTO, Basket.class))
                    .collect(Collectors.toList());

            if (basketList != null){
                User customer = userService.findUserById(customerId);
                User manager = userService.getFreeManager();
                Status status = statusService.getStatus("IN_PROCESSING");
                Order newOrder = orderService.addOrder(new Order(customer, manager, LocalDateTime.now(), status));

                List<OrderItem> orderItems = new ArrayList<>();
                basketList.forEach(basketItem -> orderItems.add(
                        orderItemService.addItem(
                                new OrderItem(
                                        newOrder,
                                        basketItem.getProduct(),
                                        basketItem.getQuantity()
                                )
                        )
                ));

                basketService.deleteItemsByCustomerId(customerId);
                return new UIResponse<>(true);
            }
            return new UIResponse<>(new NoBasketItemsException());
        }
        return new UIResponse<>(new UserExistsException());
    }

}
