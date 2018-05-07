package service.user;

import entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.user.UserRepo;

import java.util.List;

/**
 * A service interface for {@link User} entity for working with repository interface.
 */
public interface UserService {

    /**
     * A method that returns a {@link User} entity with id equals @param.
     * @param id field of {@link User} entity
     * @return a {@link User} entity
     */
    User findUserById(Long id);

    /**
     * A method that returns a {@link User} entity with login equals @param.
     * @param login field of {@link User} entity
     * @return a {@link User} entity
     */
    User findUserByLogin(String login);

    /**
     * A method that adds a new {@link User} entity in the database.
     * @param newUser a new {@link User} entity
     * @return added {@link User} entity
     */
    User addUser(User newUser);

    /**
     *
     * @param user
     * @return
     */
    User updateUser(User user);

    Boolean isUserExists(Long id);

    void deleteCustomerById(Long id);

    User getCustomerByLoginAndPassword(String login, String password);

    List<User> getAllCustomers();

    List<User> getAllManagers();

    User getFreeManager();
}
