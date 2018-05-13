package controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.UIResponse;
import entity.user.AuthorizationData;
import entity.user.Role;
import entity.user.User;
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

//    @Autowired
//    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * A method for {@link User} authorization.
     * @param data login and password
     * @return a {UserDTO} entity
     */
    @PostMapping("login")
    public UIResponse<UserDTO> login(@RequestBody AuthorizationData data){
        User user = userService.getCustomerByLoginAndPassword(data.getLogin(), data.getPassword());
        if (user != null){
            UserDTO userDTO = mapper.map(user, UserDTO.class);
            return new UIResponse<>(true, userDTO);
        }
        return new UIResponse<>(new UserNotFoundException());
    }


    /**
     * A method for registration new {@link User} entity.
     * @param data registration data
     * @return a {UserDTO} entity
     */
    @PostMapping("register")
    public UIResponse<UserDTO> register(@RequestBody AuthorizationData data){
        User newUser;
        if (userService.getCustomerByLoginAndPassword(data.getLogin(), data.getPassword()) == null){
            Role role = roleService.findRoleByTitle("ROLE_USER");
            newUser = new User(data.getFullName(), data.getLogin(), data.getPassword(), role);
            newUser = userService.addUser(newUser);
            return new UIResponse<>(true, mapper.map(newUser, UserDTO.class));
        }
        return new UIResponse<>(new UserExistsException());
    }
}
