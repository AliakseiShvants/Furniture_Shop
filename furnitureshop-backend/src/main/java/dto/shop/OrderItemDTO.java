package dto.shop;

import dto.product.ProductDTO;

import java.util.List;
import java.util.Map;

public class OrderItemDTO {

    private ProductDTO product;
    private String code;
    private Integer quantity;
    private Double price;

    public OrderItemDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
