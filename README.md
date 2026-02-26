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

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        try {
            System.out.println("=== User Registration ===");

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            System.out.print("User Type (FREE / PREMIUM): ");
            String type = scanner.nextLine();

            User user = userService.register(name, email, password, type);

            System.out.println("\nRegistered Successfully!");
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Type: " + user.getUserType());


            // ================================
            // LOGIN SECTION
            // ================================
            System.out.println("\n=== Login ===");
            System.out.println("1. Basic Login (email + password)");
            System.out.println("2. OAuth Login (dummy)");
            System.out.print("Choose option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            AuthService authService;

            if (choice == 1) {
                // BASIC AUTH
                System.out.print("Email: ");
                String loginEmail = scanner.nextLine();

                System.out.print("Password: ");
                String loginPass = scanner.nextLine();

                authService = new AuthService(new BasicAuthStrategy(userService));

                var result = authService.login(loginEmail, loginPass);

                if (result.isPresent())
                    System.out.println("Login Successful! Welcome " + result.get().getName());
                else
                    System.out.println("Login Failed.");

            } else if (choice == 2) {
                // OAUTH AUTH
                System.out.print("Enter OAuth Token: ");
                String token = scanner.nextLine();

                authService = new AuthService(new OAuthStrategy());

                var result = authService.login(token, null);

                if (result.isPresent())
                    System.out.println("OAuth Login Successful! Welcome " + result.get().getName());
                else
                    System.out.println("OAuth Login Failed. Invalid token.");

            } else {
                System.out.println("Invalid choice.");
            }

        } catch (Exception e) {
            System.out.println("\nRegistration failed: " + e.getMessage());
        }
    }
}
