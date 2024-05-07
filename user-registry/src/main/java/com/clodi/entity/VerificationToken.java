package com.clodi.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity @Table(name = "verification_token") public class VerificationToken {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;

    private String token;

    @OneToOne(cascade = CascadeType.ALL) @JoinColumn(name = "username", referencedColumnName = "username") private SimpleUser user;

    public VerificationToken() {
    }

    public VerificationToken(String token, SimpleUser user) {
        this.token = token;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SimpleUser getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

}
