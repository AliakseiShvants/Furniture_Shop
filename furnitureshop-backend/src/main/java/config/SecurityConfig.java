package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import security.Roles;
import security.UserDetailsServiceImpl;
import util.Constants;

/**
 * <p>A class for customization to the web security.
 */
@Configuration
@EnableWebSecurity
@ComponentScan(Constants.SECURITY)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl detailsService;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl detailsService) {
        this.detailsService = detailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder registry)
            throws Exception {
        registry.userDetailsService(detailsService).init(registry);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/users/admin/**").hasRole(Roles.ADMIN.name())
                .antMatchers("/api/tags/all").hasRole(Roles.ADMIN.name())
                .and()
                .httpBasic();
    }
}
