package src;

import src.menu.*;
import src.listings.*;
import src.vehicles.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleMarketplace marketplace = new VehicleMarketplace();
        VehicleFactory factory = new VehicleFactory();
        
        System.out.println("=== Vehicle Marketplace ===");
        UserProfile currentUser = initializeUser(scanner);
        
        Menu menu = new Menu(scanner);
        menu.addCommand(new ViewAllListingsCommand(marketplace));
        menu.addCommand(new AddListingCommand(scanner, marketplace, factory, currentUser));
        menu.addCommand(new SearchVehiclesCommand(scanner, marketplace));
        menu.addCommand(new ToggleNotificationsCommand(scanner, marketplace, currentUser));
        menu.addCommand(new AddSavedSearchCommand(scanner, currentUser));
        menu.addCommand(new ExitCommand());
        
        menu.show();
    }
    
    private static UserProfile initializeUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        return new UserProfile(username, email, phone);
    }
}
