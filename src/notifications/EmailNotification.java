package src.notifications;

import src.listings.Listing;
import src.listings.UserProfile;

public class EmailNotification implements NotificationStrategy {
    @Override
    public void notify(UserProfile user, Listing listing) {
        System.out.println("Sending email to: " + user.getEmail());
        System.out.println("New listing matches your search!");
        System.out.println("Vehicle: " + listing.getVehicle().getBrand() + " " + 
                          listing.getVehicle().getModel());
        System.out.println("Price: $" + listing.getVehicle().getPrice());
    }
} 