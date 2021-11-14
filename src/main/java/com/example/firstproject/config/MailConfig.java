package com.example.firstproject.config;

import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender getJavaMailSender()
    {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.rambler.ru");
        javaMailSender.setPort(465 );

        javaMailSender.setUsername("vony4ka666@rambler.ru");
        javaMailSender.setPassword("Parechnaya_94");
//        javaMailSender.setUsername("vony4ka666@rambler.ru");
//        javaMailSender.setPassword("Parechnaya_94");

        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        properties.put("mail.debug", "true");

        return javaMailSender;
    }
}
