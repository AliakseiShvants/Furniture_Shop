package domain.shop;

import domain.customer.Customer;
import domain.product.Product;

import java.util.Map;

/**
 * A object that represents customer basket.
 */
public class Basket {

    private Customer customer;
    private Map<Product, Integer> basketMap;

    public Basket() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<Product, Integer> getBasketMap() {
        return basketMap;
    }

    public void setBasketMap(Map<Product, Integer> basketMap) {
        this.basketMap = basketMap;
    }
}
