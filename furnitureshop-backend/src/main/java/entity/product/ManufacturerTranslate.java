package entity.product;

import entity.shop.Requisite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 *
 */
@Component
@Scope("prototype")
@Entity
@Table(name = "MANUFACTURER_TRANSLATE")
public class ManufacturerTranslate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MANUFACTURER_ID")
    private Long manufacturerId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "LANG_ID")
    private Long langId;

    public ManufacturerTranslate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getLangId() {
        return langId;
    }

    public void setLangId(Long langId) {
        this.langId = langId;
    }
}
