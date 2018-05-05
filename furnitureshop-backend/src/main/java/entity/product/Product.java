package entity.product;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A object that represents a product in the shop
 */
@Component
@Scope("prototype")
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "CATEGORY_ID", nullable = false, updatable = false)
    private Category category;

    @OneToOne(optional = false)
    @JoinColumn(name = "MANUFACTURER_ID")
    private Manufacturer manufacturer;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    public Product() {
    }

    public Product(String name, Category category, Manufacturer manufacturer, String description) {
        this.category = category;
        this.manufacturer = manufacturer;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

}
