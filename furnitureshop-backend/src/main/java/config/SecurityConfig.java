package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import security.Roles;
import util.Constants;

/**
 * <p>A class for customization to the web security.
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(Constants.SECURITY)
@PropertySource("classpath:application.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String API_REGISTER = "/api/register";
    private static final String API_LOGIN = "/api/login";
    private static final String API_LOGOUT = "/api/logout";
    private static final String ADMIN = Roles.ADMIN.name();

    @Autowired
    private UserDetailsService userDetailsService;

//    @Autowired
//    private JwtAuthenticationEntryPoint unauthorizedHandler;
//
//    @Bean
//    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
//        return new JwtAuthenticationFilter();
//    }

//    @Bean
//    public BCryptPasswordEncoder encoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService).init(auth);
//                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .httpBasic();
//        http.formLogin()
//                .loginPage("/api/login")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/customer/**").authenticated()
//                .and()
//                .oauth2Login();
//                .formLogin()
//                .loginPage(API_LOGIN)

//                .authorizeRequests()
//                .antMatchers("/**").permitAll();

//        http.csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/api/util/**", API_LOGIN, API_REGISTER).permitAll()
////                .antMatchers("/api/admin/**").hasRole(Roles.ADMIN.name())
////                .antMatchers("/api/manager/**").hasRole(Roles.MANAGER.name())
////                .antMatchers("/api/product/**").access("hasRole('ADMIN') and hasRole('MANAGER')")
//                .antMatchers("/api/customer/**").hasRole(Roles.USER.name())
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                    .loginPage(API_LOGIN)
//                    .permitAll();
//                .exceptionHandling()
//                .authenticationEntryPoint(unauthorizedHandler)
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http
//                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//
//        http.addFilterBefore(authenticationTokenFilterBean())


//        .logout()
//                .permitAll()
//                .logoutUrl(API_LOGOUT)
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true);
    }


}
