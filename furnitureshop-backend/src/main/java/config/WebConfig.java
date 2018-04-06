package config;

import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import static util.Constants.SERVLET_NAME;
import static util.Constants.URL_PATTERN;

/**
 * <p>A class that implements a {@link WebApplicationInitializer} interface.
 * Implementations of this interface will be detected automatically by SpringServletContainerInitializer,
 * which itself is bootstrapped automatically by any Servlet 3.0 container.
 */
@Component
public class WebConfig implements WebApplicationInitializer {

    /**
     * <p>Create the 'root' Spring application context
     * manage the lifecycle of the 'root' application context by creating in servlet context a new listener
     * create the dispatcher servlet's Spring application context
     * register and map the dispatcher servlet
     * note Dispatcher servlet with constructor arguments
     *
     * @param servletContext
     */
    @Override
    public void onStartup(ServletContext servletContext) {

        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(AppConfig.class);

        servletContext.addListener(new ContextLoaderListener(appContext));
        appContext.setServletContext(servletContext);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(SERVLET_NAME,
                new DispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(URL_PATTERN);
    }
}
