package com.springstudy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*
    dispatcher :
    Servlet WebAppContext: --> view resolver, controller, handler mapping, handler adapter
    Root WebAppContext: --> service, repository, datasource, transaction manager, security
     */

    @Override
    protected Class<?>[] getRootConfigClasses() { // Root WebAppContext, access to data : JDBC, JPA, Hibernate
        return new Class[] {
                RootConfig.class
        };

    }

    @Override
    protected Class<?>[] getServletConfigClasses() { //view resolver, controller, handler mapping, handler adapter configuration
       return new Class<?>[] {
               WebMvcConfig.class
       };
    }

    @Override
    protected String[] getServletMappings() { //dispatcher servlet mapping, which url will be handled by dispatcher servlet
        return new String[] {
                "/"
        };

    }
}
