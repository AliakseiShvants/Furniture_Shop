package dto.shop;

import dto.product.ProductDTO;

import java.util.List;
import java.util.Map;

public class OrderDetailsDTO {

    private ProductDTO product;
    private Integer quantity;
    private Double price;

    public OrderDetailsDTO() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
