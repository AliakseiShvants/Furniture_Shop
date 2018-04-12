package controller.user;

import domain.product.Product;
import domain.shop.Order;
import domain.user.Admin;
import domain.user.Manager;
import domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.product.ProductService;
import service.shop.OrderService;
import service.user.UserService;

import java.util.List;

/**
 * <p>Controller class for {@link Admin} entity.
 */
@RestController
@RequestMapping("api/admin/")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    /**
     * A method that returns all customers
     * @return list of customers
     */
    @GetMapping("customer/all")
    public ResponseEntity<List<User>> getAllCustomers(){
        return new ResponseEntity<>(userService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("manager/all")
    public ResponseEntity<List<Manager>> getAllManagers(){
        return new ResponseEntity<>(userService.getAllManagers(), HttpStatus.OK);
    }

    @GetMapping("order/all")
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("product/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }



}
