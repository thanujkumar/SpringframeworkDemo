package org.tk.spring.mvc.servlet3;

import org.springframework.web.HttpRequestHandler;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.ServletWrappingController;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

//Needs to be packaged into war or use spring boot to initialize

//With Spring support for Servlet 3.0 Specification, web.xml is optional and we can manage all our web application using Java Annotations
public class SampleWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.setServletContext(servletContext);
        //webApplicationContext.scan("org.tk.spring.mvc.servlet3");
        //OR
        webApplicationContext.register(WebConfig.class);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("test", new DispatcherServlet(webApplicationContext)); //Dispatcher Servlet is known as an entry point for Spring MVC based web applications
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    //TODO other low level API's that can be used
    HttpRequestHandler requestHandler; //supports remoting
    DelegatingFilterProxy filterProxy; //javax.servlet.Filter that delegate to a spring-managed lifecycle
    HandlerInterceptor handlerInterceptor;// wraps requests to HttpRequestHandler
    ServletWrappingController wrappingController; //lets you force requests to a servlet through spring handler chain
    OncePerRequestFilter oncePerRequestFilter;//ensures that action occurs only once
    WebApplicationContextUtils contextUtils; // has static method to lookup the current ApplicationContext given a ServletContext
}
