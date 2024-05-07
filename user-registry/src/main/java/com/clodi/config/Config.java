package com.clodi.config;

import java.util.List;
import java.util.Properties;

import com.clodi.dao.SimpleUserRepository;
import com.clodi.entity.SimpleRole;
import com.clodi.entity.SimpleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Claudia Vidican
 */
@Configuration public class Config {

    @Autowired private PasswordEncoder passwordEncoder;

    @Bean public CommandLineRunner demo(SimpleUserRepository repository) {
        return (args) -> {
            SimpleUser clau = new SimpleUser("clau", passwordEncoder.encode("pass"), true, "email@email.com");
            SimpleUser admin = new SimpleUser("admin", passwordEncoder.encode("pass"), true, "email2@email.com");
            SimpleUser dan = new SimpleUser("dan", passwordEncoder.encode("123"), true, "email3@email.com");

            SimpleRole clauAuth = new SimpleRole(clau, SimpleRole.ADMIN);
            SimpleRole adminAuth = new SimpleRole(admin, SimpleRole.ADMIN);
            SimpleRole danAuth = new SimpleRole(dan, SimpleRole.ADMIN);

            clau.setRoles(List.of(clauAuth));
            admin.setRoles(List.of(adminAuth));
            dan.setRoles(List.of(danAuth));

            if (repository.findByUsername(clau.getUsername()).isEmpty()) {
                repository.save(clau);
            }
            if (repository.findByUsername(admin.getUsername()).isEmpty()) {
                repository.save(admin);
            }
            if (repository.findByUsername(dan.getUsername()).isEmpty()) {
                repository.save(dan);
            }
        };
    }

    @Bean public MailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setProtocol("smtp");
        javaMailSender.setUsername("claudiaivanovnak@gmail.com");
        javaMailSender.setPassword("bmqrvdhmsmvaedhb");
        Properties mailProperties = new Properties();

        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.smtp.starttls.enable", "true");
        mailProperties.put("mail.smtp.debug", "true");

        javaMailSender.setJavaMailProperties(mailProperties);
        return javaMailSender;
    }

}
