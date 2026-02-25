package com.yourcompany.mycontactapp.profile.command;

public class CommandInvoker {

    public void executeCommand(ProfileCommand command) {
        command.execute();
    }
}