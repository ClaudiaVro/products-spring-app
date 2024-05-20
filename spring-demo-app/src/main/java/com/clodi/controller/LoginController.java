package com.clodi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Claudia Vidican
 */
@Controller
public class LoginController {

    @GetMapping("/login") String login() {
        return "login-form";
    }
}
