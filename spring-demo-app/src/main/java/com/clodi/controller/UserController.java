package com.clodi.controller;

import com.clodi.dto.SimpleUserDTO;
import com.clodi.exception.UserNotFoundException;
import com.clodi.proxy.UserProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Claudia Vidican
 */
@Controller @RequestMapping("/user") public class UserController {

    private static final String FORM = "registration-form-simple";
    private final UserProxy userProxy;

    public UserController(UserProxy userProxy) {
        this.userProxy = userProxy;
    }

    @GetMapping("/registration") public String showRegistrationForm(Model model) {
        SimpleUserDTO dto = new SimpleUserDTO();
        model.addAttribute("user", dto);
        return FORM;
    }

    @PostMapping("/logout") public void logout() {
        userProxy.logout();
    }

    @PostMapping("/registration") public String registerUserAccount(@ModelAttribute("user") SimpleUserDTO dto, BindingResult bindingResult,
                                                                    Model model) {
        if (bindingResult.hasErrors()) {
            return FORM;
        }

        if (userProxy.registerUserAccount(dto)) {
            return "redirect:/home";
        }
        else {
            // in practice, we really shouldn't show the user that a username already exists
            model.addAttribute("user", new SimpleUserDTO());
            model.addAttribute("usernameExists", "Username already exists");
            return FORM;
        }
    }

    @GetMapping("/confirmRegistration") public String confirmRegistration(@RequestParam("token") String token) {
        try {
            return userProxy.confirmRegistration(token);
        }
        catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

}
