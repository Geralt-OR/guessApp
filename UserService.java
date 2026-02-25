package service;

import com.yourcompany.mycontactapp.user.model.User;
import com.yourcompany.mycontactapp.user.builder.UserBuilder;
import com.yourcompany.mycontactapp.user.factory.UserFactory;
import com.yourcompany.mycontactapp.user.validation.EmailValidator;
import com.yourcompany.mycontactapp.user.validation.PasswordValidator;
import com.yourcompany.mycontactapp.user.validation.PasswordHasher;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<User> users = new ArrayList<>();

    public User register(String name, String email, String password, String type) throws Exception {

        if (!EmailValidator.isValid(email))
            throw new Exception("Invalid email format");

        if (!PasswordValidator.isValid(password))
            throw new Exception("Password too short");

        String hashed = PasswordHasher.hash(password);

        UserBuilder builder = new UserBuilder()
                .setName(name)
                .setEmail(email)
                .setHashedPassword(hashed);

        User user = UserFactory.createUser(type, builder);
        users.add(user);

        return user;
    }

    public List<User> getUsers() {
        return users;
    }
}