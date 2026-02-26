package com.yourcompany.mycontactapp.contact.builder;

import com.yourcompany.mycontactapp.contact.model.PersonContact;
import com.yourcompany.mycontactapp.contact.model.Contact;
import com.yourcompany.mycontactapp.contact.model.PhoneNumber;

public class ContactBuilder {

    private String name;
    private PhoneNumber phone;
    private String email;

    public ContactBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ContactBuilder setPhone(String phone) {
        this.phone = new PhoneNumber(phone);
        return this;
    }

    public ContactBuilder setEmail(String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            throw new IllegalArgumentException("Invalid email!");
        this.email = email;
        return this;
    }

    // For UC4 simplification: only PersonContact
    public Contact buildPerson() {
        return new PersonContact(name, phone, email);
    }
}
