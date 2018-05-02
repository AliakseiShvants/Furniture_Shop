package service.user;

import domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.user.UserRepo;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User getUserByLogin(String login){
        return userRepo.findUserByLogin(login);
    }

    public User addUser(User newUser) {
        return userRepo.save(newUser);
    }

    public User getUserById(Long id) {
        if (userRepo.findById(id).isPresent()){
            return userRepo.findById(id).get();
        }
        return null;
    }

    public User updateUser(User user){
        return userRepo.saveAndFlush(user);
    }

    public Boolean isUserExists(Long id){
        return userRepo.existsById(id);
    }

    public void deleteCustomerById(Long id) {
        userRepo.deleteById(id);
    }

    public User getCustomerByLoginAndPassword(String login, String password) {
        return userRepo.findUserByLoginAndPassword(login, password);
    }

    public List<User> getAllCustomers() {
        return userRepo.findAllCustomers();
    }

    public List<User> getAllManagers() {
        return userRepo.findAllManagers();
    }

    public User getFreeManager() {
        return userRepo.findManagerByMinimalOrdersCount();
    }
}
