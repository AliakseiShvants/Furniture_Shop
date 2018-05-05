package controller.user;

import entity.UIResponse;
import entity.user.AuthorizationData;
import entity.user.Role;
import entity.user.User;
import dto.product.ProductDTO;
import dto.shop.OrderDTO;
import dto.shop.RequisiteDTO;
import dto.user.UserDTO;
import exception.UserExistsException;
import exception.UserNotFoundException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.product.ProductService;
import service.shop.OrderService;
import service.shop.RequisiteService;
import service.user.RoleService;
import service.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Controller class for {@link User} entity with 'ROLE_ADMIN' role.
 */
@Deprecated
@RestController
@RequestMapping("api/admin/")
public class AdminController {

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private RequisiteService requisiteService;

    /**
     * A method that returns all {@link User} entity with 'ROLE_USER' role.
     * @return list of customers
     */
    @GetMapping("customer/all")
    public UIResponse<List<UserDTO>> getAllCustomers(){
        List<User> customers = userService.getAllCustomers();
        if (customers != null){
            List<UserDTO> userDTOS = customers.stream()
                    .map(customer -> mapper.map(customer, UserDTO.class))
                    .collect(Collectors.toList());

            userDTOS.forEach(userDTO -> userDTO.setRequisite(
                    mapper.map(
                            requisiteService.getRequisiteByUserId(userDTO.getId()), RequisiteDTO.class
                    )
            ));
            return new UIResponse<>(true, userDTOS);
        }
        return new UIResponse<>(new UserNotFoundException());
    }

    /**
     * A method that updates {@link User} entity
     * @param userDTO 
     * @return
     */
    @PatchMapping("user/update")
    public UIResponse<Void> updateUser(@RequestBody UserDTO userDTO){
        if (userService.isUserExists(userDTO.getId())){
            User dbUser = userService.getUserById(userDTO.getId());
            dbUser.setFullName(userDTO.getFullName());
            dbUser.setLogin(userDTO.getLogin());
            dbUser.setPassword(userDTO.getPassword());
            dbUser.setEmail(userDTO.getEmail());
            dbUser.setBirthday(userDTO.getBirthday());
            dbUser.setSex(userDTO.getSex());
            userService.updateUser(dbUser);
            return new UIResponse<>(true);
        }
        return new UIResponse<>(new UserNotFoundException());
    }

    /**
     * A method that adds new {@link User} entity with 'ROLE_USER' role.
     * @param data
     * @return
     */
    @PostMapping("customer/add")
    public UIResponse<Void> addCustomer(@RequestBody AuthorizationData data){
        User newUser;
        if (userService.getCustomerByLoginAndPassword(data.getLogin(), data.getPassword()) == null){
            Role role = roleService.getRoleByTitle("ROLE_USER");
            newUser = new User(data.getFullName(), data.getLogin(), data.getPassword(),
                    data.getEmail(), role);
            userService.addUser(newUser);
            return new UIResponse<>(true);
        }
        return new UIResponse<>(new UserExistsException());
    }

    /**
     * A method that returns all {@link User} entity with 'ROLE_MANAGER' role.
     * @return list of {@link UserDTO} entities.
     */
    @GetMapping("manager/all")
    public UIResponse<List<UserDTO>> getAllManagers(){
        return new UIResponse<>(true, userService.getAllManagers().stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList())
        );
    }

    /**
     * A method that adds new {@link User} entity with 'ROLE_MANAGER' role.
     * @param managerDTO a {@link UserDTO} entity
     * @return new {@link UserDTO} entity
     */
    @PostMapping("manager/add")
    public UIResponse<UserDTO> addManager(@RequestBody UserDTO managerDTO){
        Role role = roleService.getRoleByTitle("ROLE_MANAGER");
        User manager = new User(managerDTO.getFullName(), managerDTO.getLogin(), managerDTO.getPassword(),
                managerDTO.getEmail(), role);
        manager = userService.addUser(manager);
        return new UIResponse<>(true, mapper.map(manager, UserDTO.class));
    }

    /**
     * A method that returns all {@link OrderDTO} entities
     * @return list of {@link OrderDTO} entities
     */
    @GetMapping("order/all")
    public UIResponse<List<OrderDTO>> getAllOrders(){
        return new UIResponse<>(true, orderService.getAll().stream()
                .map(order -> mapper.map(order, OrderDTO.class))
                .collect(Collectors.toList())
        );
    }

    /**
     * A method that returns all {@link ProductDTO} entities
     * @return list of {@link ProductDTO} entities
     */
    @GetMapping("product/all")
    public UIResponse<List<ProductDTO>> getAllProducts(){
        return new UIResponse<>(true, productService.findAllProducts().stream()
                .map(product -> mapper.map(product, ProductDTO.class))
                .collect(Collectors.toList())
        );
    }


}
