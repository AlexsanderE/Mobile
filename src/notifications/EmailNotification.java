package src.notifications;

import src.listings.Listing;
import src.users.UserProfile;

public class EmailNotification implements NotificationStrategy {
    @Override
    public void notify(UserProfile user, Listing listing) {
        System.out.println("Sending email to: " + user.getEmail());
        System.out.println("New listing matches your search!");
        System.out.println("Vehicle: " + listing.getVehicle().getBrand() + " " + 
                          listing.getVehicle().getModel());
        System.out.println("Price: bgn" + listing.getVehicle().getPrice());
    }
} 