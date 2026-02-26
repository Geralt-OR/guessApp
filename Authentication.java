package com.yourcompany.mycontactapp.auth;

import com.yourcompany.mycontactapp.user.model.User;
import java.util.Optional;

public interface Authentication {
    Optional<User> authenticate(String identifier, String password);
}