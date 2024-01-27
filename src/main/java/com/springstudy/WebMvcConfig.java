package com.springstudy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = {"com.springstudy"}) //it will scan all the components in the package
@EnableWebMvc //it will enable spring mvc
public class WebMvcConfig implements WebMvcConfigurer  {

    //ViewResolver
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/views/");//all the views will be in this folder
        viewResolver.setSuffix(".jsp");//all the views will be in this format
        viewResolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);//we are using jstl view

        return viewResolver;
    }

    //Static Resources don't need to be handled by DispatcherServlet
    //DispatcherServlet will handle only dynamic resources
    //Static resources will be handled by default servlet
    //We need to configure default servlet handler
    //We need to override addResourceHandlers method
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //this method will handle static resources
        //we need to tell where the static resources are located
        //we need to tell where the static resources will be served from
        registry.addResourceHandler("/statics/**").
                addResourceLocations("/resources/").
                setCachePeriod(0);//we don't want to cache static resources

    }




}
