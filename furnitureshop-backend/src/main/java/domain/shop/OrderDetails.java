package domain.shop;

import domain.customer.Customer;
import domain.product.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * A object that represents concrete info about order (order name, products categories and their amount.
 */
public class OrderDetails {
    private Long id;
    private Order order;
    private Customer customer;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<Product, Integer> getProductQuantityMap() {
        return productQuantityMap;
    }

    public void setProductQuantityMap(Map<Product, Integer> productQuantityMap) {
        this.productQuantityMap = productQuantityMap;
    }
}
