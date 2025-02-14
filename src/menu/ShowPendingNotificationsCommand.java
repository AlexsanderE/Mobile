package src.menu;

import src.listings.Listing;
import src.users.UserProfile;
import src.vehicles.Vehicle;
import java.util.List;

public class ShowPendingNotificationsCommand implements MenuCommand {
    private final UserProfile user;
    
    public ShowPendingNotificationsCommand(UserProfile user) {
        this.user = user;
    }
    
    @Override
    public String getDescription() {
        return "Show pending notifications";
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== Your Pending Notifications ===");
        List<Listing> notifications = user.getPendingNotifications();
        
        if (notifications.isEmpty()) {
            System.out.println("No new notifications.");
            return;
        }
        
        for (Listing listing : notifications) {
            Vehicle vehicle = listing.getVehicle();
            System.out.println("\nNew matching listing found!");
            System.out.printf("Vehicle: %s %s %d%n", 
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getYear());
            System.out.printf("Price: bgn%.2f%n", vehicle.getPrice());
            System.out.printf("Seller: %s%n", listing.getSeller().getUsername());
            System.out.println("------------------------");
        }
    }
} 