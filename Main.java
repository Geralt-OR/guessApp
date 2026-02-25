import service.UserService;
import service.AuthService;

import com.yourcompany.mycontactapp.user.model.User;

import com.yourcompany.mycontactapp.auth.strategy.BasicAuthStrategy;
import com.yourcompany.mycontactapp.auth.session.SessionManager;

import com.yourcompany.mycontactapp.profile.command.*;

import java.util.Scanner;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        try {

            // =========================
            // UC1 - USER REGISTRATION
            // =========================
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


            // =========================
            // UC2 - USER LOGIN
            // =========================
            System.out.println("\n=== Login ===");

            System.out.print("Email: ");
            String loginEmail = scanner.nextLine();

            System.out.print("Password: ");
            String loginPass = scanner.nextLine();

            AuthService authService =
                    new AuthService(new BasicAuthStrategy(userService));

            Optional<User> result =
                    authService.login(loginEmail, loginPass);

            if (result.isEmpty()) {
                System.out.println("Login Failed.");
                return;
            }

            System.out.println("Login Successful! Welcome "
                    + result.get().getName());


            // =========================
            // UC3 - PROFILE MANAGEMENT
            // =========================
            User loggedUser =
                    SessionManager.getInstance().getLoggedInUser();

            CommandInvoker invoker = new CommandInvoker();

            System.out.println("\n=== Profile Management ===");
            System.out.println("1. Change Name");
            System.out.println("2. Change Email");
            System.out.println("3. Change Password");
            System.out.print("Choose option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    invoker.executeCommand(
                            new UpdateNameCommand(loggedUser, newName)
                    );
                    break;

                case 2:
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    invoker.executeCommand(
                            new UpdateEmailCommand(loggedUser, newEmail)
                    );
                    break;

                case 3:
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    invoker.executeCommand(
                            new ChangePasswordCommand(loggedUser, newPassword)
                    );
                    break;

                default:
                    System.out.println("Invalid choice.");
                    return;
            }

            System.out.println("\nProfile Updated Successfully!");
            System.out.println("Updated Name: " + loggedUser.getName());
            System.out.println("Updated Email: " + loggedUser.getEmail());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}