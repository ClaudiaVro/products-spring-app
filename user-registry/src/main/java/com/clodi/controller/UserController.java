package com.clodi.controller;

import java.util.Optional;

import com.clodi.activation.OnRegistrationSuccessEvent;
import com.clodi.dto.SimpleUserDTO;
import com.clodi.entity.SimpleUser;
import com.clodi.entity.VerificationToken;
import com.clodi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/user") public class UserController {

    private static final String FORM = "registration-form-simple";

    @Autowired private UserService userService;

    @Autowired private ApplicationEventPublisher eventPublisher;

    @PostMapping("/login") public String doLogin(Model model) {
        return "redirect:/home";
    }

    @PostMapping("/registration") public boolean registerUserAccount(@RequestBody SimpleUserDTO user) {
        Optional<SimpleUser> existingUsername = userService.findUserByUsername(user.getUsername());
        Optional<SimpleUser> existingUsernameByEmail = userService.findUserByEmail(user.getEmail());

        if (existingUsername.isPresent() || existingUsernameByEmail.isPresent()) {
            return false;
        }
        else {
            SimpleUser saved = userService.registerNewUserAccount(user);
            eventPublisher.publishEvent(new OnRegistrationSuccessEvent(saved));
        }
        return true;
    }

    @GetMapping("/confirmRegistration") public String confirmRegistration(@RequestParam("token") String token) {
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            return "redirect:access-denied";
        }

        SimpleUser user = verificationToken.getUser();
        if (user != null) {
            user.setEnabled(true);
            userService.enableRegisteredUser(user);
        }
        else {
            //            throw new UsernameNotFoundException("for token " + token);
        }
        return "registration-successful";
    }
}
