package com.kaynaak.rest.config;

import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class AppConfiguration {
	// class 1: org.springframework.context.support.ResourceBundleMessageSource
	// class 2:
	// org.springframework.context.support.ReloadableResourceBundleMessageSource;
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
		//setGmail(mailSender);
		setYahooMail(mailSender);
		return mailSender;
	}
	
	void setGmail(JavaMailSenderImpl mailSender) {
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("hanv2050@gmail.com");
		mailSender.setPassword("ngotngao");
	
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
	}
	
	void setYahooMail(JavaMailSenderImpl mailSender) {
		mailSender.setHost("smtp.mail.yahoo.com");
		mailSender.setPort(587);
		mailSender.setUsername("kaynaak.app@yahoo.com");
		mailSender.setPassword("App@123Kay");
	
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
	}
	
	void setSmtp2go(JavaMailSenderImpl mailSender) {
		mailSender.setHost("mail.smtp2go.com");
		mailSender.setPort(25);
		mailSender.setUsername("hanguyenvan127@gmail.com");
		mailSender.setPassword("123456a@");
	
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
	}
}
