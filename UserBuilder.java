package com.yourcompany.mycontactapp.user.builder;

public class UserBuilder {

    public String name;
    public String email;
    public String hashedPassword;

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
        return this;
    }
}