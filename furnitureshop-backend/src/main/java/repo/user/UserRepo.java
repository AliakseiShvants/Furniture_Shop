package repo.user;

import domain.user.Manager;
import domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Long> {

    User findUserByLogin(String login);

    User findUserByLoginAndPassword(String login, String password);

    @Query(value = "SELECT u.user_id, u.fullname, u.login, u.password, u.email, u.role_id, u.requisite_id, u.birthday, u.sex\n" +
            "FROM furniture_shop.users u\n" +
            "  LEFT JOIN furniture_shop.role r\n" +
            "    ON u.role_id = r.role_id\n" +
            "WHERE r.title = 'ROLE_USER'", nativeQuery = true)
    List<User> findAllUsers();

    @Query(value = "SELECT u.user_id, u.fullname, u.login, u.password, u.email, u.role_id, u.requisite_id, u.birthday, u.sex, " +
            "u.team\n" +
            "FROM furniture_shop.users u\n" +
            "  LEFT JOIN furniture_shop.role r\n" +
            "    ON u.role_id = r.role_id\n" +
            "WHERE r.title = 'ROLE_MANAGER'", nativeQuery = true)
    List<Manager> findAllManagers();


}
