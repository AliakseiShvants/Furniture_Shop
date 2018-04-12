package domain.shop;

import domain.user.User;
import domain.product.Product;

import java.util.Map;

/**
 * A object that represents user basket.
 */
public class Basket {

    private User user;
    private Map<Product, Integer> basketMap;

    public Basket() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Product, Integer> getBasketMap() {
        return basketMap;
    }

    public void setBasketMap(Map<Product, Integer> basketMap) {
        this.basketMap = basketMap;
    }
}
