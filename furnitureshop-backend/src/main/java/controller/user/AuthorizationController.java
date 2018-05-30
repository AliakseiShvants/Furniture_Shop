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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import service.user.RoleService;
import service.user.UserService;

import java.util.Locale;

@RestController
@RequestMapping("/api/")
public class AuthorizationController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private PasswordEncoder encoder;

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
        User user = userService.getCustomerByLogin(data.getLogin());
        if (encoder.matches(data.getPassword(), user.getPassword())){
            UserDTO userDTO = mapper.map(user, UserDTO.class);
            LOG.info(messageSource.getMessage("user.login", new Object[]{user.getLogin()}, Locale.ENGLISH));
            return new UIResponse<>(true, userDTO);
        }
        LOG.error(messageSource.getMessage("user.login.error", new Object[]{data.getLogin()}, Locale.ENGLISH));
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
        if (userService.getCustomerByLogin(data.getLogin()) == null){
            Role role = roleService.findRoleByTitle("ROLE_USER");
            newUser = new User(data.getFullName(), data.getLogin(), data.getEmail(), role);
            newUser.setPassword(encoder.encode(data.getPassword()));
            newUser = userService.addUser(newUser);
            LOG.info(messageSource.getMessage("user.register", new Object[]{newUser.getLogin()}, Locale.ENGLISH));
            return new UIResponse<>(true, mapper.map(newUser, UserDTO.class));
        }
        LOG.error(messageSource.getMessage("user.register.error", new Object[]{data.getLogin()}, Locale.ENGLISH));
        return new UIResponse<>(new UserExistsException());
    }
}
