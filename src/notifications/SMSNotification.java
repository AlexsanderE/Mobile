package src.notifications;

import src.listings.Listing;
import src.users.UserProfile;

public class SMSNotification implements NotificationStrategy {
    @Override
    public void notify(UserProfile user, Listing listing) {
        System.out.println("Sending SMS to: " + user.getPhone());
        System.out.println("New listing: " + listing.getVehicle().getBrand() + 
                          " " + listing.getVehicle().getModel() + 
                          " Price: bgn" + listing.getVehicle().getPrice());
    }
} 