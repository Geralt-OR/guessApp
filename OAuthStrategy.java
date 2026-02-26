package com.yourcompany.mycontactapp.auth.strategy;

import com.yourcompany.mycontactapp.auth.Authentication;
import com.yourcompany.mycontactapp.user.model.User;

import java.util.Optional;

public class OAuthStrategy implements Authentication {

    @Override
    public Optional<User> authenticate(String token, String ignored) {
        return Optional.empty();   // Not implemented
    }
}