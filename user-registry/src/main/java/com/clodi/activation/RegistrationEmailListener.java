package com.clodi.activation;

import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

import com.clodi.entity.SimpleUser;
import com.clodi.exception.EmailErrorException;
import com.clodi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component public class RegistrationEmailListener implements ApplicationListener<OnRegistrationSuccessEvent> {

    @Autowired private UserService userService;
    @Autowired private MailSender mailSender;
    @Autowired private MessageSource messages;
    @Value("${name.mainapp.url}") private String appName;

    @Override public void onApplicationEvent(OnRegistrationSuccessEvent event) {
        confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationSuccessEvent event) {

        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "false");

        SimpleUser user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipient = user.getEmail();
        String subject = "Registration Confirmation";
        String url = "/user/confirmRegistration?token=" + token;
        String message;
        try {
            message = messages.getMessage("message.registrationSuccessConfirmationLink", null, Locale.getDefault());
        }
        catch (Throwable thr) {
            message = "Attempted to retrieve message for Successful message";
        }

        SimpleMailMessage email = new SimpleMailMessage();
        try {
            email.setTo(recipient);
            email.setSubject(subject);
            email.setText(message + " " + appName + url);
            mailSender.send(email);
        }
        catch (Exception ex) {
            throw new EmailErrorException(ex.getMessage());
        }
    }

}
