package entity.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 *
 */
@Component
@Scope("prototype")
@Entity
@Table(name = "USERS_TRANSLATE")
public class UserTranslate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "FULLNAME")
    private String fullName;

    @Column(name = "LANG_ID")
    private Long langId;

    public UserTranslate() {
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getLangId() {
        return langId;
    }

    public void setLangId(Long langId) {
        this.langId = langId;
    }
}
