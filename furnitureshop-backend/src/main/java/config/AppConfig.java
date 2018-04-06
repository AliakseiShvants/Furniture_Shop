package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import util.Constants;

/**
 * <p>Class that provides a configuration to the application context
 */
@Configuration
@PropertySource(Constants.APP_PROP)
@EnableTransactionManagement
@Import(value = {
                    SecurityConfig.class,
                    WebMvcConfig.class,
                    DBConnectionConfig.class
        })
public class AppConfig {

    @Value("${" + Constants.MESSAGE_SOURCE_NAME + "}")
    private String messageSourceName;

    @Value("${" + Constants.ENCODING + "}")
    private String encoding;

    @Value("${" + Constants.CACHE_SECONDS + "}")
    private Integer cacheSeconds;

    /**
     * <P>Spring-specific {@link MessageSource} implementation for resolving messages, with support for
     * the parameterization and internationalization of such messages.
     * Participates in the Spring ApplicationContext's resource loading.
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {

        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasenames(messageSourceName);
        source.setCacheSeconds(cacheSeconds);
        source.setDefaultEncoding(encoding);
        return source;
    }
}
