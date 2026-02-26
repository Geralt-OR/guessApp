package com.yourcompany.mycontactapp.contact.view;

public class MaskedEmailDecorator implements ContactView {

    private ContactView inner;

    public MaskedEmailDecorator(ContactView inner) {
        this.inner = inner;
    }

    @Override
    public String getFormattedDetails() {
        String data = inner.getFormattedDetails();

        // mask email — convert something@example.com -> s******@example.com
        return data.replaceAll(
                "([A-Za-z0-9])([A-Za-z0-9._%+-]*)(@.+)",
                "$1******$3"
        );
    }
}
