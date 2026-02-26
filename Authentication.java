import java.util.Optional;

public interface Authentication {
    Optional<User> authenticate(String identifier, String password);
}