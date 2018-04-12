package domain.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.shop.Requisite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * A object that represents a user in the shop.
 */
@Component
@Scope("prototype")
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "FULLNAME")
    private String fullName;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    @Column(name = "SEX")
    private Boolean sex;

    @OneToOne(optional = false)
    @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "REQUISITE_ID")
    private Requisite requisite;

    public User() {
    }

    public User(String fullName, String login, String password, String email, Role role) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(String fullName, String email, LocalDate birthday, Boolean sex) {
        this.fullName = fullName;
        this.email = email;
        this.birthday = birthday;
        this.sex = sex;
    }

    public Requisite getRequisite() {
        return requisite;
    }

    public void setRequisite(Requisite requisite) {
        this.requisite = requisite;
    }

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
