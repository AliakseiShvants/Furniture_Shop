package repo.user;

import entity.user.User;
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

    @Query(value = "SELECT u.user_id, u.fullname, u.login, u.password, u.email, u.role_id, u.requisite_id, u.birthday," +
            " u.sex\n" +
            "FROM furniture_shop.users u\n" +
            "  LEFT JOIN furniture_shop.role r\n" +
            "    ON u.role_id = r.role_id\n" +
            "WHERE r.title = 'ROLE_USER'", nativeQuery = true)
    List<User> findAllCustomers();

    @Query(value = "SELECT u.user_id, u.fullname, u.login, u.password, u.email, u.role_id, u.requisite_id, u.birthday," +
            " u.sex\n" +
            "FROM furniture_shop.users u\n" +
            "  LEFT JOIN furniture_shop.role r\n" +
            "    ON u.role_id = r.role_id\n" +
            "WHERE r.title = 'ROLE_MANAGER'", nativeQuery = true)
    List<User> findAllManagers();


    @Query(value = "SELECT u.user_id, u.fullname, u.login, u.password, u.email, u.role_id, u.requisite_id, u.birthday, " +
            "u.sex\n" +
            "FROM furniture_shop.users u\n" +
            "JOIN (\n" +
            "    SELECT u.user_id, count(o.order_id) AS ordersCount\n" +
            "    FROM furniture_shop.users u\n" +
            "      JOIN furniture_shop.role r\n" +
            "        ON u.role_id = r.role_id\n" +
            "      LEFT JOIN furniture_shop.orders o\n" +
            "        ON u.user_id = o.manager_id\n" +
            "    WHERE r.title LIKE '%MANAGER%'\n" +
            "    GROUP BY u.user_id\n" +
            "    ) uro ON u.user_id = uro.user_id\n" +
            "ORDER BY ordersCount\n" +
            "LIMIT 1", nativeQuery = true)
    User findManagerByMinimalOrdersCount();

    void deleteById(Long id);
}
