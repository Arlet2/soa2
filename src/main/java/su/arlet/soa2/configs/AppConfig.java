package su.arlet.soa2.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class AppConfig {

    @Autowired
    void configureDispatcherServlet( DispatcherServlet dispatcherServlet ) {
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }
}