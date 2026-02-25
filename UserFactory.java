package com.yourcompany.mycontactapp.user.factory;

import com.yourcompany.mycontactapp.user.model.*;
import com.yourcompany.mycontactapp.user.builder.UserBuilder;

public class UserFactory {

    public static User createUser(String type, UserBuilder builder) {

        switch (type.toUpperCase()) {
            case "FREE":
                return new FreeUser(builder.name, builder.email, builder.hashedPassword);

            case "PREMIUM":
                return new PremiumUser(builder.name, builder.email, builder.hashedPassword);

            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }
}
