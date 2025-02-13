package src.notifications;

import src.listings.Listing;

public interface Observer {
    void update(Listing newListing);
} 