package src.menu;

import src.listings.VehicleMarketplace;
import src.listings.UserProfile;
import src.notifications.NotificationType;
import java.util.Scanner;

public class ToggleNotificationsCommand implements MenuCommand {
    private final Scanner scanner;
    private final VehicleMarketplace marketplace;
    private final UserProfile user;
    
    public ToggleNotificationsCommand(Scanner scanner, VehicleMarketplace marketplace, UserProfile user) {
        this.scanner = scanner;
        this.marketplace = marketplace;
        this.user = user;
    }
    
    @Override
    public String getDescription() {
        return "Manage notifications";
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== Notification Settings ===");
        System.out.println("1. Subscribe to email notifications");
        System.out.println("2. Subscribe to SMS notifications");
        System.out.println("3. Subscribe to both notifications");
        System.out.println("4. Unsubscribe from all notifications");
        System.out.print("Choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    marketplace.getNotificationService().subscribe(user, NotificationType.EMAIL);
                    System.out.println("Subscribed to email notifications!");
                    break;
                case 2:
                    marketplace.getNotificationService().subscribe(user, NotificationType.SMS);
                    System.out.println("Subscribed to SMS notifications!");
                    break;
                case 3:
                    marketplace.getNotificationService().subscribe(user, NotificationType.BOTH);
                    System.out.println("Subscribed to both notifications!");
                    break;
                case 4:
                    marketplace.getNotificationService().unsubscribe(user);
                    System.out.println("Unsubscribed from all notifications!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
} 