package com.clodi.model;

import java.io.Serializable;
import java.util.List;

public class SimpleUser implements Serializable {

    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private List<SimpleRole> roles;

    private String email;

    public SimpleUser() {
    }

    public SimpleUser(String username, String password, boolean enabled, String email) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SimpleRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SimpleRole> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
