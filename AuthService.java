package service;

import com.yourcompany.mycontactapp.auth.Authentication;
import com.yourcompany.mycontactapp.auth.session.SessionManager;
import com.yourcompany.mycontactapp.user.model.User;

import java.util.Optional;

public class AuthService {

    private Authentication strategy;

    public AuthService(Authentication strategy) {
        this.strategy = strategy;
    }

    public Optional<User> login(String identifier, String password) {

        Optional<User> userOpt = strategy.authenticate(identifier, password);

        userOpt.ifPresent(user -> {
            SessionManager.getInstance().startSession(user);
        });

        return userOpt;
    }
}