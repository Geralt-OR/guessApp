import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserService {

    private final List<User> users = new ArrayList<>();

    public User register(String name, String email, String password, String type) {

        // Validations
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (!EmailValidator.isValid(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!PasswordValidator.isValid(password)) {
            throw new IllegalArgumentException("Password too short");
        }

        String hashed = PasswordHasher.hash(password);

        String normalized = normalizeType(type);
        User user = ("PREMIUM".equals(normalized))
                ? new PremiumUser(name, email, hashed)
                : new FreeUser(name, email, hashed);

        users.add(user);
        return user;
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    private String normalizeType(String type) {
        if (type == null) return "FREE";
        String t = type.trim().toUpperCase();
        if ("PREMIUM".equals(t)) return "PREMIUM";
        if ("FREE".equals(t)) return "FREE";
        return "FREE";
    }
}
