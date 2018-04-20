package dto.user;

import domain.user.AuthorizationData;
import dto.shop.RequisiteDTO;

import java.time.LocalDate;

public class UserDTO {

    private Long id;
    private String fullName;
    private String email;
    private LocalDate birthday;
    private RequisiteDTO requisite;
    private Boolean sex;
    private String role;

    public UserDTO() {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
