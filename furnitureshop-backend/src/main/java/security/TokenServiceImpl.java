package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

public class TokenServiceImpl {

    @Autowired
    private UserDetailsService userDetailsService;

}
