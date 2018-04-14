package dto.product;

import static util.Constants.COMMA;


public class ProductDTO {

    private Long id;
    private String code;
    private String[] url;
    private String category;
    private String manufacturer;
    private String name;
    private String description;
    private Double price;

    public ProductDTO() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String[] getUrl() {
        return url;
    }

    public void setUrl(String[] url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name)
                .append(COMMA)
                .append(code)
                .append(COMMA)
                .append(manufacturer);
        return sb.toString();
    }
}
