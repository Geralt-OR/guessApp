package com.yourcompany.mycontactapp.profile.command;

import com.yourcompany.mycontactapp.user.model.User;

public class UpdateEmailCommand implements ProfileCommand {

    private User user;
    private String newEmail;

    public UpdateEmailCommand(User user, String newEmail) {
        this.user = user;
        this.newEmail = newEmail;
    }

    @Override
    public void execute() {
        user.setEmail(newEmail);
    }
}