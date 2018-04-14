package controller.user;

import domain.UIResponse;
import domain.product.Product;
import domain.shop.BasketItem;
import domain.shop.OrderDetails;
import domain.shop.Requisite;
import domain.shop.StorageItem;
import domain.user.AuthorizationData;
import domain.user.Role;
import domain.user.User;
import dto.shop.OrderDTO;
import dto.shop.OrderDetailsDTO;
import dto.shop.RequisiteDTO;
import dto.user.UserDTO;
import exception.*;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.product.ProductService;
import service.shop.*;
import service.user.RoleService;
import service.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Controller class for {@link User} entity.
 */
@RestController
@RequestMapping("api/customer/")
public class CustomerController {

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RequisiteService requisiteService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailsService detailsService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private StorageService storageService;

    /**
     * A method for user's authorization.
     * @param body login and password
     * @return special user entity
     */
    @PostMapping("login")
    public UIResponse<UserDTO> login(@RequestBody String[] body){
        User user = userService.getCustomerByLoginAndPassword(body);
        if (user != null){
            UserDTO userDTO = mapper.map(user, UserDTO.class);
            return new UIResponse<>(true, userDTO);
        }
        return new UIResponse<>(new UserNotFoundException());
    }

    /**
     * A method for registration new user
     * @param data registration data
     * @return special user entity
     */
    @PostMapping("register")
    public UIResponse<UserDTO> register(@RequestBody AuthorizationData data){
        User newUser;
        if (userService.getCustomerByLoginAndPassword(data.getLogin(), data.getPassword()) == null){
            Role role = roleService.getRoleByTitle("ROLE_USER");
            newUser = new User(data.getFullName(), data.getLogin(), data.getPassword(),
                    data.getEmail(), role);
            newUser = userService.addUser(newUser);
            return new UIResponse<>(true, mapper.map(newUser, UserDTO.class));
        }
        return new UIResponse<>(new UserExistsException());
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
        User user = userService.getUserById(id);
        return user != null ? new UIResponse<>(true, mapper.map(user, UserDTO.class))
                : new UIResponse<>(new UserNotFoundException());
    }

    /**
     * A method for updating user's profile info
     * @param id user id
     * @param updateUser user entity with updated fields
     * @return user entity
     */
    @PatchMapping("{id}/update")
    public UIResponse<UserDTO> updateProfile(@PathVariable Long id, @RequestBody UserDTO updateUser){
        if (userService.isUserExists(id)){
            User user = new User(updateUser.getFullName(), updateUser.getEmail(), updateUser.getBirthday(),
                    updateUser.getSex());
            user = userService.updateUser(user);
            return new UIResponse<>(true, mapper.map(user, UserDTO.class));
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
        User user = userService.getUserById(id);
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
     * @param requisiteId
     * @param dto
     * @return
     */
    @PatchMapping("{customerId}/requisite/{requisiteId}/update")
    public UIResponse<RequisiteDTO> updateRequisite(@PathVariable Long customerId, @PathVariable Long requisiteId,
                                             @RequestBody RequisiteDTO dto){
        if (userService.isUserExists(customerId)){
            if (requisiteService.isRequisiteExist(requisiteId)){
                Requisite requisite = new Requisite(dto.getZip(), dto.getCountry(), dto.getCity(), dto.getAddress());
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
    @GetMapping("{id}/orders")
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
    @GetMapping("{customerId}/orders/{orderId}")
    public UIResponse<List<OrderDetailsDTO>> getOrderInfo(@PathVariable Long customerId, @PathVariable Long orderId){
        if (userService.isUserExists(customerId)){
            if (orderService.isOrderExists(orderId)){
                List<OrderDetailsDTO> detailsList = detailsService.getDetailsByOrderId(orderId).stream()
                        .map(details -> mapper.map(details, OrderDetailsDTO.class))
                        .collect(Collectors.toList());
                for (OrderDetailsDTO orderDetailsDTO: detailsList){
                    StorageItem storageItem = storageService.getStorageByProductId(orderDetailsDTO.getProduct().getId());
                    orderDetailsDTO.setPrice(storageItem.getPrice());
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
        if (userService.isUserExists(customerId)){
            if (productService.isProductExists(productId)){
                if (storageService.isItemExists(productId) && storageService.isAvailable(productId, 1)){
                    User customer = userService.getUserById(customerId);
                    Product product = productService.getProductById(productId);
                    BasketItem basketItem = new BasketItem(customer, product, 1);
                    basketService.addBasketItem(basketItem);
                    return new UIResponse<>(true);
                }
                return new UIResponse<>(new StorageNotFoundException());
            }
            return new UIResponse<>(new ProductExistsException());
        }
        return new UIResponse<>(new UserExistsException());
    }


//    public UIResponse<List<>>


}
