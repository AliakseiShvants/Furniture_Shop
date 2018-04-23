package domain.product;

import domain.shop.Requisite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * A object that represents manufacturer of product
 */
@Component
@Scope("prototype")
@Entity
@Table(name = "MANUFACTURER")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MANUFACTURER_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "REQUISITE_ID")
    private Requisite requisite;

    public Manufacturer() {
    }

    public Manufacturer(String title, Requisite requisite) {
        this.title = title;
        this.requisite = requisite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Requisite getRequisite() {
        return requisite;
    }

    public void setRequisite(Requisite requisite) {
        this.requisite = requisite;
    }
}
