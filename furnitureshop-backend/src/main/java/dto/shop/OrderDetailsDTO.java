package dto.shop;

import dto.product.ProductDTO;

import java.util.List;
import java.util.Map;

public class OrderDetailsDTO {

    private ProductDTO product;
    private Integer quantity;

    public OrderDetailsDTO() {
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
