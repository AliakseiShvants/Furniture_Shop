package service.impl;

import entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.user.UserRepo;
import service.user.UserService;

import java.util.List;
/**
 * A implementation of {@link UserService} interface
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User findUserByLogin(String login) {
        return userRepo.findUserByLogin(login);
    }

    @Override
    public User addUser(User newUser) {
        return userRepo.save(newUser);
    }

    @Override
    public User findUserById(Long id) {
        if (userRepo.findById(id).isPresent()){
            return userRepo.findById(id).get();
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        return userRepo.saveAndFlush(user);
    }

    @Override
    public Boolean isUserExists(Long id) {
        return userRepo.existsById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User getCustomerByLoginAndPassword(String login, String password) {
        return userRepo.findUserByLoginAndPassword(login, password);
    }

    @Override
    public List<User> getAllCustomers() {
        return userRepo.findAllCustomers();
    }

    @Override
    public List<User> getAllManagers() {
        return userRepo.findAllManagers();
    }

    @Override
    public User getFreeManager() {
        return userRepo.findManagerByMinimalOrdersCount();
    }
}
