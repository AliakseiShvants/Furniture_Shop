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
     * A method that updates a {@link User} entity
     * @param user {@link User} entity with updated fields
     * @return new {@link User} entity
     */
    User updateUser(User user);

    /**
     * A method that checks if a {@link User} entity exists in the database.
     * @param id field of {@link User} entity
     * @return true if {@link User} entity is deleted
     */
    Boolean isUserExists(Long id);

    /**
     * A method that deletes a {@link User} entity by it id field.
     * @param id field of {@link User} entity
     */
    void deleteUserById(Long id);

    User findByFullName(String name);

    User getCustomerByLoginAndPassword(String login, String password);

    List<User> getAllCustomers();

    List<User> getAllManagers();

    User getFreeManager();
}
