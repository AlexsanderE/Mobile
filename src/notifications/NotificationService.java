package src.notifications;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import src.listings.Listing;
import src.users.UserProfile;
import src.search.SearchCriteria;

public class NotificationService {
    private List<UserProfile> subscribers = new ArrayList<>();
    private Map<UserProfile, NotificationType> userPreferences = new HashMap<>();
    private final NotificationStrategy emailStrategy = new EmailNotification();
    private final NotificationStrategy smsStrategy = new SMSNotification();
    
    public void subscribe(UserProfile user, NotificationType type) {
        if (!subscribers.contains(user)) {
            subscribers.add(user);
        }
        userPreferences.put(user, type);
        System.out.println("DEBUG: User " + user.getUsername() + " subscribed with " + type);
    }
    
    public void unsubscribe(UserProfile user) {
        subscribers.remove(user);
        userPreferences.remove(user);
    }
    
    public void updatePreference(UserProfile user, NotificationType type) {
        if (subscribers.contains(user)) {
            userPreferences.put(user, type);
        }
    }
    
    public void notifyUsers(Listing newListing) {
        System.out.println("DEBUG: Processing new listing: " + 
            newListing.getVehicle().getBrand() + " " + 
            newListing.getVehicle().getModel());
            
        for (UserProfile user : subscribers) {
            System.out.println("DEBUG: Checking user: " + user.getUsername() + 
                             " (Subscribed: " + user.isSubscribedToNotifications() + ")");
                             
            for (SearchCriteria criteria : user.getSavedSearches()) {
                boolean matches = criteria.matches(newListing.getVehicle());
                System.out.println("DEBUG: Checking criteria - Match found: " + matches);
                
                if (matches) {
                    System.out.println("DEBUG: Notifying user: " + user.getUsername());
                    notifyUser(user, newListing);
                    break;
                }
            }
        }
    }
    
    private void notifyUser(UserProfile user, Listing listing) {
        NotificationType preference = user.getNotificationPreference();
        
        switch (preference) {
            case EMAIL:
                emailStrategy.notify(user, listing);
                break;
            case SMS:
                smsStrategy.notify(user, listing);
                break;
            case BOTH:
                emailStrategy.notify(user, listing);
                smsStrategy.notify(user, listing);
                break;
            case NONE:
                break;
        }
        
        // Store notification for later viewing
        user.addPendingNotification(listing);
    }
    
    private boolean isUserOnline(UserProfile user) {
        
        return false; // For now, store all notifications as pending
    }
    
    private List<UserProfile> getAllSubscribedUsers() {
        return subscribers;
    }
} 