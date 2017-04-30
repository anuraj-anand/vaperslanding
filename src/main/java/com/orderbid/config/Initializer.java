package com.orderbid.config;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
 
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Initializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext)
    		throws ServletException {
	   AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
       annotationConfigWebApplicationContext.register(WebConfig.class);
       annotationConfigWebApplicationContext.setServletContext(servletContext);
        
       Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(annotationConfigWebApplicationContext));
       dynamic.addMapping("/");
       dynamic.setLoadOnStartup(1);
	
    }
 
}