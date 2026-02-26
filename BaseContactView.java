package com.yourcompany.mycontactapp.contact.view;
//Concrete Component
import com.yourcompany.mycontactapp.contact.model.Contact;

public class BaseContactView implements ContactView {

    private Contact contact;

    public BaseContactView(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String getFormattedDetails() {
        return  "ID: " + contact.getId() + "\n" +
                "Name: " + contact.getName() + "\n" +
                "Phone: " + contact.getPhone().getNumber() + "\n" +
                "Email: " + contact.getEmail() + "\n" +
                "Type: " + contact.getContactType() + "\n" +
                "Created: " + contact.getCreatedAt();
    }
}
