package domain.shop;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.user.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * A object that represents a user's order
 */
@Component
@Scope("prototype")
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false, updatable = false)
    private User user;

    @OneToOne(optional = false)
    @JoinColumn(name = "MANAGER_ID", nullable = false, updatable = false)
    private User manager;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "CREATE_DATE")
    private LocalDateTime creatingDate;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "FINISH_DATE")
    private LocalDateTime completionDate;

    public Order() {
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(LocalDateTime creatingDate) {
        this.creatingDate = creatingDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
