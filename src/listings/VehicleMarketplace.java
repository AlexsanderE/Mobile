package src.listings;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import src.notifications.NotificationService;
import src.search.SearchCriteria;

public class VehicleMarketplace {
    private List<Listing> listings;
    private NotificationService notificationService;
    
    public VehicleMarketplace() {
        this.listings = new ArrayList<>();
        this.notificationService = new NotificationService();
    }
    
    public void addListing(Listing listing) {
        listings.add(listing);
        notificationService.notifyUsers(listing);
    }
    
    public List<Listing> search(SearchCriteria criteria) {
        return listings.stream()
            .filter(listing -> criteria.matches(listing.getVehicle()))
            .collect(Collectors.toList());
    }
    
    public NotificationService getNotificationService() {
        return notificationService;
    }
    
    public List<Listing> getAllListings() {
        return new ArrayList<>(listings);
    }
} 