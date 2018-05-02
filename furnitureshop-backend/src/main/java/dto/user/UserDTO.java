package dto.user;

import domain.user.Role;
import dto.shop.RequisiteDTO;

import java.time.LocalDate;

public class UserDTO {

    private Long id;
    private String fullName;
    private String login;
    private String password;
    private String email;
    private LocalDate birthday;
    private RequisiteDTO requisite;
    private Boolean sex;
    private Role role;

    public UserDTO() {
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

    public RequisiteDTO getRequisite() {
        return requisite;
    }

    public void setRequisite(RequisiteDTO requisite) {
        this.requisite = requisite;
    }

    public Long getId() {
        return id;
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
