package com.kaynaak.rest.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class AppConfiguration {
	//class 1: org.springframework.context.support.ResourceBundleMessageSource
	//class 2: org.springframework.context.support.ReloadableResourceBundleMessageSource;
	@Bean
	public MessageSource validationMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages/validation_resources");
		messageSource.setCacheSeconds(1000);
		return messageSource;
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages/service_resources");
		messageSource.setCacheSeconds(1000);
		return messageSource;
	}
}
