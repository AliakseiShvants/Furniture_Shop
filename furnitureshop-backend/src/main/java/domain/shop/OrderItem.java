package domain.shop;

import domain.product.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * A object that represents concrete info about order (order name, products categories and their amount).
 */
@Component
@Scope("prototype")
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "ORDER_ID",  nullable = false, updatable = false)
    private Order order;

    @OneToOne(optional = false)
    @JoinColumn(name = "PRODUCT_ID",  nullable = false, updatable = false)
    private Product product;

    @Column(name = "QUANTITY")
    private Integer quantity;

    public OrderItem() {
    }

    public OrderItem(Order order, Product product, Integer quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
