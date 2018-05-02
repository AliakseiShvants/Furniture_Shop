package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import security.UserDetailsServiceImpl;
import util.Constants;

import java.util.Locale;

import static util.Constants.*;

/**
 * <p>A class that implements {@linkplain WebMvcConfigurer} interface through a {@link WebMvcConfigurerAdapter} class.
 *
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {
        CONTROLLERS,
        SERVICES
})
@EnableJpaRepositories(REPOSITORIES)
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    /**
     *<p>Retrieving and saving the user's local settings.
     */
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver r = new SessionLocaleResolver();
        r.setDefaultLocale(new Locale(Constants.DEFAULT_LANG, Constants.DEFAULT_COUNTRY));
        return r;
    }

    /**
     * <p>Interceptor that allows for changing the current locale on every request,
     * via a configurable request parameter (default parameter name: "locale").
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        return new LocaleChangeInterceptor();
    }

    /**
     * <p>The choice of class and its method that should handle the incoming request based on any internal or external
     * for this query attribute or state.
     * @return
     */
    @Bean
    public HandlerMapping handlerMapping(){
        RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
        handlerMapping.setInterceptors(localeChangeInterceptor());
        return handlerMapping;
    }

    /**
     * <p>Defines resource folder
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("*").addResourceLocations("");
    }

    /**
     * Forwards a request for 'URL_PATTERN' to a view called 'INDEX'
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }
}
