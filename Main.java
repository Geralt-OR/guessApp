import service.UserService;
import service.AuthService;

import com.yourcompany.mycontactapp.user.model.User;

import com.yourcompany.mycontactapp.auth.strategy.BasicAuthStrategy;
import com.yourcompany.mycontactapp.auth.session.SessionManager;

import com.yourcompany.mycontactapp.profile.command.*;

import com.yourcompany.mycontactapp.contact.builder.ContactBuilder;
import com.yourcompany.mycontactapp.contact.factory.ContactFactory;
import com.yourcompany.mycontactapp.contact.model.Contact;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        try {

            // ===================================
            // UC1 - USER REGISTRATION
            // ===================================
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


            // ===================================
            // UC2 - LOGIN
            // ===================================
            System.out.println("\n=== Login ===");

            System.out.print("Email: ");
            String loginEmail = scanner.nextLine();

            System.out.print("Password: ");
            String loginPass = scanner.nextLine();

            AuthService authService =
                    new AuthService(new BasicAuthStrategy(userService));

            Optional<User> loginResult =
                    authService.login(loginEmail, loginPass);

            if (loginResult.isEmpty()) {
                System.out.println("Login Failed.");
                return;
            }

            System.out.println("Login Successful! Welcome "
                    + loginResult.get().getName());

            User loggedUser = SessionManager.getInstance().getLoggedInUser();


            // ===================================
            // UC3 - PROFILE MANAGEMENT (Simple)
            // ===================================
            System.out.println("\n=== Profile Management ===");
            System.out.println("1. Change Name");
            System.out.println("2. Skip");
            System.out.print("Choose option: ");

            int profileChoice = Integer.parseInt(scanner.nextLine());

            if (profileChoice == 1) {
                CommandInvoker invoker = new CommandInvoker();
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();

                invoker.executeCommand(
                        new UpdateNameCommand(loggedUser, newName)
                );

                System.out.println("Name Updated!");
            }


            // ===================================
            // UC4 - CREATE CONTACT (Simple)
            // ===================================
            System.out.println("\n=== Create Contact ===");

            System.out.print("Contact Name: ");
            String cName = scanner.nextLine();

            System.out.print("Phone Number (10 digits): ");
            String cPhone = scanner.nextLine();

            System.out.print("Email: ");
            String cEmail = scanner.nextLine();

            ContactBuilder cb = new ContactBuilder()
                    .setName(cName)
                    .setPhone(cPhone)
                    .setEmail(cEmail);

            Contact contact =
                    ContactFactory.createContact("PERSON", cb);

            System.out.println("\nContact Created Successfully!");
            System.out.println("ID: " + contact.getId());
            System.out.println("Created: " + contact.getCreatedAt());
            System.out.println("Type: " + contact.getContactType());
            System.out.println("Name: " + contact.getName());
            System.out.println("Phone: " + contact.getPhone().getNumber());
            System.out.println("Email: " + contact.getEmail());

            // ===================================
            // UC5 - VIEW CONTACT DETAILS
            // ===================================
            System.out.println("\n=== View Contact Details ===");

            ContactView view = new BaseContactView(contact);

            view = new MaskedEmailDecorator(view);   
            view = new UpperCaseDecorator(view);     

            System.out.println("\nFormatted Contact Details:");
            System.out.println(view.getFormattedDetails());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}