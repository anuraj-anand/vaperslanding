package com.orderbid.config;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.velocity.VelocityEngineFactory;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages={"com.orderbid"})
@EnableWebMvc
@EnableTransactionManagement
public class WebConfig  extends WebMvcConfigurerAdapter{
	
	@Bean
	 public InternalResourceViewResolver configureInternalResourceViewResolver() {
	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	        resolver.setPrefix("/WEB-INF/views/");
	        resolver.setSuffix(".jsp");
	        return resolver;
	 }
	 
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(52428800);
        return multipartResolver;
    }

    @Bean
    public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("application.properties"));
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations(
                "/resources/");
    }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
   	 LocaleChangeInterceptor result = new LocaleChangeInterceptor() {
   		 @Override
   		 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
   			throws ServletException {
   		// Proceed in any case.
   		return super.preHandle(request, response, handler);
   	}
   	 };       
   	 result.setParamName("locale");
   	return result;
    }	
    
    @Bean
 	 public SessionLocaleResolver localeResolver() {
		//CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		//localeResolver.setCookieName("locale");
		//resolver.setDefaultLocale(new Locale("en"));
		return resolver;
	 }
    
    @Bean
	 public MessageSource messageSource() {
		ResourceBundleMessageSource result = new ResourceBundleMessageSource();
		String[] basenames = {
				"messages"
		};
		result.setBasenames(basenames);
		return result;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
   
    @Bean
    public JavaMailSenderImpl getJavaMailSender() {
    	return new JavaMailSenderImpl();
    }
    
    @Bean
    public VelocityEngine getVelocityEngine() 
      throws VelocityException, IOException{
      VelocityEngineFactory factory = new VelocityEngineFactory();
      Properties props = new Properties();
      props.put("resource.loader", "class");
      props.put("class.resource.loader.class", 
                "org.apache.velocity.runtime.resource.loader." + 
                "ClasspathResourceLoader");
      factory.setVelocityProperties(props);
      return factory.createVelocityEngine();
    }
    
 /*   @Bean
	public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
		MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
		obj.setTargetBeanName("jobone");
		obj.setTargetMethod("myTask");
		return obj;
	}*/
	/*@Bean
	public SimpleTriggerFactoryBean simpleTriggerFactoryBean(){
		SimpleTriggerFactoryBean stFactory = new SimpleTriggerFactoryBean();
		stFactory.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
		stFactory.setStartDelay(3000);
		stFactory.setRepeatInterval(30000);
		stFactory.setRepeatCount(3);
		return stFactory;
	}*/
/*	@Bean
	public JobDetailFactoryBean jobDetailFactoryBean(){
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(MyJobTwo.class);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "RAM");
		map.put(MyJobTwo.COUNT, 1);
		factory.setJobDataAsMap(map);
		factory.setGroup("mygroup");
		factory.setName("myjob");
		return factory;
	}
	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean(){
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(jobDetailFactoryBean().getObject());
		stFactory.setStartDelay(3000);
		stFactory.setName("mytrigger");
		stFactory.setGroup("mygroup");
		stFactory.setCronExpression("0 0/1 * 1/1 * ? *");
		return stFactory;
	}
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(simpleTriggerFactoryBean().getObject(),cronTriggerFactoryBean().getObject());
		return scheduler;
	}*/
}