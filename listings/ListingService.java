package listings;

import notifications.NotificationService;

public class ListingService {
    private final ListingStorage listingStorage;
    private final NotificationService notificationService;

    public ListingService(ListingStorage listingStorage, NotificationService notificationService) {
        this.listingStorage = listingStorage;
        this.notificationService = notificationService;
    }

    public void addListing(Listing listing) {
        listingStorage.addListing(listing);
        notificationService.onNewListingAdded(listing);
    }
}