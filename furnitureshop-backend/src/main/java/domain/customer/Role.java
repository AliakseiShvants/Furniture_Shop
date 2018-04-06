package domain.customer;

import domain.customer.Customer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * A simple JavaBean object that represents a role of {@link Customer} entity.
 */
@Component
@Scope("prototype")
@Entity
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(String title) {
        this.title = title;
    }

    public Role(Long id, String title) {
        this(id);
        this.title = title;
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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
