package org.popcorntech.bidsystem.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name",length = 60,nullable = false)
    private String full_name;

    @Column(name = "email",length = 100,nullable = false)
    private String email;

    @Column(name = "password",length = 60,nullable = false)
    private String password;

    public User() {
    }

    public User(String full_name, String email, String password) {
        this.full_name = full_name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getFull_name() {
        return full_name;
    }

    public User setFull_name(String full_name) {
        this.full_name = full_name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
}
