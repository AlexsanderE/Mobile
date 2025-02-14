package src.menu;

import src.listings.VehicleMarketplace;
import src.listings.Listing;
import src.vehicles.Vehicle;
import java.util.List;

public class ViewAllListingsCommand implements MenuCommand {
    private final VehicleMarketplace marketplace;
    
    public ViewAllListingsCommand(VehicleMarketplace marketplace) {
        this.marketplace = marketplace;
    }
    
    @Override
    public String getDescription() {
        return "View all listings";
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== All Available Listings ===");
        List<Listing> listings = marketplace.getAllListings();
        
        if (listings.isEmpty()) {
            System.out.println("No listings available.");
            return;
        }
        
        for (Listing listing : listings) {
            Vehicle vehicle = listing.getVehicle();
            System.out.printf("%s %s %d - bgn%.2f (Seller: %s)%n",
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getPrice(),
                listing.getSeller().getUsername());
        }
    }
} 