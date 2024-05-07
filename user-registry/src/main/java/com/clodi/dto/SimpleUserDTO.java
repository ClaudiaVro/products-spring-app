package com.clodi.dto;

import com.clodi.validation.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@PasswordMatches(message = "The password fields must match", password = "password") public class SimpleUserDTO {

    @NotNull(message = "can't be empty") @Size(min = 2, max = 10, message = "must be between 2 and 10 characters") private String username;
    @NotNull(message = "can't be empty") @Size(min = 3, message = "must be at least 3 chars") private String password;
    @Email private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String matchingPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

}
