package controller.user;

import domain.UIResponse;
import domain.product.Product;
import domain.shop.Order;
import domain.user.AuthorizationData;
import domain.user.Role;
import domain.user.User;
import dto.shop.RequisiteDTO;
import dto.user.UserDTO;
import exception.UserExistsException;
import exception.UserNotFoundException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.product.ProductService;
import service.shop.OrderService;
import service.shop.RequisiteService;
import service.user.RoleService;
import service.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Controller class for {@link User} entity with 'ADMIN' role.
 */
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
     * A method that returns all customers
     * @return list of customers
     */
    @GetMapping("customers")
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

    @PostMapping("addCustomer")
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

    @GetMapping("managers")
    public ResponseEntity<List<User>> getAllManagers(){
        return new ResponseEntity<>(userService.getAllManagers(), HttpStatus.OK);
    }

    @GetMapping("orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }



}
