package src.notifications;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import src.listings.Listing;
import src.listings.UserProfile;
import src.search.SearchCriteria;

import java.util.ArrayList;

public class NotificationService {
    private List<UserProfile> subscribers = new ArrayList<>();
    private Map<UserProfile, NotificationType> userPreferences = new HashMap<>();
    private final NotificationStrategy emailStrategy = new EmailNotification();
    private final NotificationStrategy smsStrategy = new SMSNotification();
    
    public void subscribe(UserProfile user, NotificationType type) {
        subscribers.add(user);
        userPreferences.put(user, type);
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
        for (UserProfile user : subscribers) {
            for (SearchCriteria criteria : user.getSavedSearches()) {
                if (criteria.matches(newListing.getVehicle())) {
                    notifyUser(user, newListing);
                    break;
                }
            }
        }
    }
    
    private void notifyUser(UserProfile user, Listing listing) {
        NotificationType preference = userPreferences.getOrDefault(user, NotificationType.NONE);
        
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
    }
} 