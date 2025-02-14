package src.listings;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import src.notifications.NotificationService;
import src.search.SearchCriteria;

public class VehicleMarketplace {
    private ListingManager listingManager;
    private NotificationService notificationService;
    
    public VehicleMarketplace() {
        this.listingManager = new ListingManager();
        this.notificationService = new NotificationService();
    }
    
    public void addListing(Listing listing) {
        listingManager.saveListing(listing);
        notificationService.notifyUsers(listing);
    }
    
    public List<Listing> search(SearchCriteria criteria) {
        return listingManager.getAllListings().stream()
            .filter(listing -> criteria.matches(listing.getVehicle()))
            .collect(Collectors.toList());
    }
    
    public NotificationService getNotificationService() {
        return notificationService;
    }
    
    public List<Listing> getAllListings() {
        return listingManager.getAllListings();
    }
} 