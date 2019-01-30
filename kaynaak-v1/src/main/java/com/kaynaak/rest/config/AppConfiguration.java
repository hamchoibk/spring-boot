package com.kaynaak.rest.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class AppConfiguration {
	
	@Value("${spring.mail.host}")
    private String host;
	
	@Value("${spring.mail.port}")
    private int port;
	
	@Value("${spring.mail.username}")
    private String username;
	
	@Value("${spring.mail.password}")
    private String password;
	
	@Value("${spring.mail.debug}")
	private boolean mailDebug;
    
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private boolean isStarttls;
	
	@Bean
	public MessageSource validationMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages/validation_resources");
		messageSource.setCacheSeconds(3600);
		return messageSource;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages/service_resources");
		messageSource.setCacheSeconds(3600);
		return messageSource;
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
	
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		if(isStarttls) {
			props.put("mail.smtp.starttls.enable", "true");
		}
		if(mailDebug) {
			props.put("mail.debug", "true");
		}
		return mailSender;
	}
	
}
