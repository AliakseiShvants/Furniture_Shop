package controller.user;

import domain.UIResponse;
import domain.user.AuthorizationData;
import domain.user.Role;
import domain.user.User;
import dto.user.UserDTO;
import exception.UserExistsException;
import exception.UserNotFoundException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.user.RoleService;
import service.user.UserService;

@RestController
@RequestMapping("/api/")
public class AuthorizationController {

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * A method for user's authorization.
     * @param body login and password
     * @return special user entity
     */
    @PostMapping("login")
    public UIResponse<UserDTO> login(@RequestBody String[] body){
        User user = userService.getCustomerByLoginAndPassword(body);
        if (user != null){
            UserDTO userDTO = mapper.map(user, UserDTO.class);
            return new UIResponse<>(true, userDTO);
        }
        return new UIResponse<>(new UserNotFoundException());
    }

    /**
     * A method for registration new user
     * @param data registration data
     * @return special user entity
     */
    @PostMapping("register")
    public UIResponse<UserDTO> register(@RequestBody AuthorizationData data){
        User newUser;
        if (userService.getCustomerByLoginAndPassword(data.getLogin(), data.getPassword()) == null){
            Role role = roleService.getRoleByTitle("ROLE_USER");
            newUser = new User(data.getFullName(), data.getLogin(), data.getPassword(),
                    data.getEmail(), role);
            newUser = userService.addUser(newUser);
            return new UIResponse<>(true, mapper.map(newUser, UserDTO.class));
        }
        return new UIResponse<>(new UserExistsException());
    }
}
