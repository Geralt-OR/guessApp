package com.yourcompany.mycontactapp.contact.factory;

import com.yourcompany.mycontactapp.contact.builder.ContactBuilder;
import com.yourcompany.mycontactapp.contact.model.Contact;

public class ContactFactory {

    public static Contact createContact(String type, ContactBuilder builder) {

        switch (type.toUpperCase()) {
            case "PERSON":
                return builder.buildPerson();

            default:
                throw new IllegalArgumentException("Invalid contact type");
        }
    }
}