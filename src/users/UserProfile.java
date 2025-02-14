package src.users;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import src.listings.Listing;
import src.search.SearchCriteria;
import src.notifications.NotificationType;

public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String email;
    private String phone;
    private String password;
    private List<Listing> listings;
    private List<SearchCriteria> savedSearches;
    private List<Listing> pendingNotifications;
    private NotificationType notificationPreference = NotificationType.NONE;
    
    public UserProfile(String username, String email, String phone, String password) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.listings = new ArrayList<>();
        this.savedSearches = new ArrayList<>();
        this.pendingNotifications = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }

    public List<SearchCriteria> getSavedSearches() {
        return savedSearches;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void addPendingNotification(Listing listing) {
        pendingNotifications.add(listing);
    }
    
    public List<Listing> getPendingNotifications() {
        List<Listing> notifications = new ArrayList<>(pendingNotifications);
        pendingNotifications.clear(); // Clear after receiving notifications
        return notifications;
    }

    public NotificationType getNotificationPreference() {
        return notificationPreference;
    }
    
    public void setNotificationPreference(NotificationType type) {
        this.notificationPreference = type;
    }
    
    public boolean isSubscribedToNotifications() {
        return notificationPreference != NotificationType.NONE;
    }
} 