package com.yourcompany.mycontactapp.contact.view;

public class UpperCaseDecorator implements ContactView {

    private ContactView inner;

    public UpperCaseDecorator(ContactView inner) {
        this.inner = inner;
    }

    @Override
    public String getFormattedDetails() {
        return inner.getFormattedDetails().toUpperCase();
    }
}
