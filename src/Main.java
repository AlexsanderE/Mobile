package src;

import src.menu.*;
import src.listings.*;
import src.vehicles.*;
import src.users.UserProfile;
import src.users.UserProfileManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleMarketplace marketplace = new VehicleMarketplace();
        VehicleFactory factory = new VehicleFactory();
        UserProfileManager userManager = new UserProfileManager();
        
        while (true) {
            System.out.println("\n=== Vehicle Marketplace ===");
            UserProfile currentUser = handleUserAuth(scanner, userManager);
            
            Menu menu = new Menu(scanner);
            menu.addCommand(new ViewAllListingsCommand(marketplace));
            menu.addCommand(new AddListingCommand(scanner, marketplace, factory, currentUser));
            menu.addCommand(new SearchVehiclesCommand(scanner, marketplace));
            menu.addCommand(new ToggleNotificationsCommand(scanner, marketplace, currentUser));
            menu.addCommand(new AddSavedSearchCommand(scanner, currentUser));
            menu.addCommand(new ShowPendingNotificationsCommand(currentUser));
            menu.addCommand(new LogoutCommand(menu));
            menu.addCommand(new ExitCommand());
            
            menu.show();
        }
    }
    
    private static UserProfile handleUserAuth(Scanner scanner, UserProfileManager userManager) {
        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.print("Choice: ");
            
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                UserProfile user = handleLogin(scanner, userManager);
                if (user != null) {
                    new ShowPendingNotificationsCommand(user).execute();
                    return user;
                }
            } else if (choice.equals("2")) {
                return handleRegistration(scanner, userManager);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
    
    private static UserProfile handleLogin(Scanner scanner, UserProfileManager userManager) {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        UserProfile user = userManager.getUser(username);
        if (user != null && user.checkPassword(password)) {
            System.out.println("Login successful!");
            return user;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }
    
    private static UserProfile handleRegistration(Scanner scanner, UserProfileManager userManager) {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        if (userManager.userExists(username)) {
            System.out.println("Username already exists.");
            return handleUserAuth(scanner, userManager);
        }
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        UserProfile newUser = new UserProfile(username, email, phone, password);
        userManager.saveUser(newUser);
        System.out.println("Registration successful!");
        return newUser;
    }
}
