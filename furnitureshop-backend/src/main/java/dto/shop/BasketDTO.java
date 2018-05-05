package dto.shop;

import dto.product.ProductDTO;

/**
 * A class that represents a position in the basket sending to UI
 */
public class BasketDTO {

    private Long id;
    private ProductDTO product;
    private Integer quantity;
    private Double price;

    public BasketDTO() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
