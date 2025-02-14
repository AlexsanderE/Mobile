package src.menu;

import src.listings.VehicleMarketplace;
import src.users.UserProfile;
import src.notifications.NotificationType;
import java.util.Scanner;
import src.users.UserProfileManager;

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
        
        String choice = scanner.nextLine();
        NotificationType type;
        
        switch (choice) {
            case "1":
                type = NotificationType.EMAIL;
                marketplace.getNotificationService().subscribe(user, type);
                break;
            case "2":
                type = NotificationType.SMS;
                marketplace.getNotificationService().subscribe(user, type);
                break;
            case "3":
                type = NotificationType.BOTH;
                marketplace.getNotificationService().subscribe(user, type);
                break;
            case "4":
                type = NotificationType.NONE;
                marketplace.getNotificationService().unsubscribe(user);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        
        user.setNotificationPreference(type);
        
        // Save the updated user profile
        UserProfileManager userManager = new UserProfileManager();
        userManager.saveUser(user);
        
        System.out.println("Notification preferences updated successfully!");
    }
} 