package domain.shop;

import domain.user.User;
import domain.product.Product;

import java.util.Map;

/**
 * A object that represents concrete info about order (order name, products categories and their amount.
 */
public class OrderDetails {
    private Long id;
    private Order order;
    private User user;
    private Map<Product, Integer> productQuantityMap;

    public OrderDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Product, Integer> getProductQuantityMap() {
        return productQuantityMap;
    }

    public void setProductQuantityMap(Map<Product, Integer> productQuantityMap) {
        this.productQuantityMap = productQuantityMap;
    }
}
