import java.util.Optional;

public class OAuthStrategy implements Authentication {

    @Override
    public Optional<User> authenticate(String token, String ignored) {

        // Simple dummy logic
        if ("OAUTH-123".equals(token)) {
            // Login as a dummy OAuth user
            User oauthUser = new PremiumUser(
                    "OAuth User",
                    "oauth@example.com",
                    PasswordHasher.hash("oauthpass")
            );

            return Optional.of(oauthUser);
        }

        return Optional.empty();
    }
}
