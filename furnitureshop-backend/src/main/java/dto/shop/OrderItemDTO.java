package dto.shop;

import dto.product.ProductDTO;

import java.util.List;
import java.util.Map;

public class OrderItemDTO {

    private ProductDTO product;
    private String code;
    private Integer quantity;
    private Double total;

    public OrderItemDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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
