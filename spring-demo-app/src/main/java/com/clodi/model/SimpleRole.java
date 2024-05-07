package com.clodi.model;

import java.io.Serializable;

public class SimpleRole implements Serializable {
    private Long id;
    private SimpleUser user;
    private String role;

    public SimpleRole() {
    }

    public SimpleRole(SimpleUser user, String role) {
        this.user = user;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public SimpleUser getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

}
