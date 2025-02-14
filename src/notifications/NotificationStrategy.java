package src.notifications;

import src.listings.Listing;
import src.users.UserProfile;

public interface NotificationStrategy {
    void notify(UserProfile user, Listing listing);
} 