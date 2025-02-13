package src.listings;
import java.time.LocalDateTime;

import src.vehicles.Vehicle;


public class Listing {
    private Vehicle vehicle;
    private double price;
    private UserProfile seller;
    private LocalDateTime createdAt;
    
    public Listing(Vehicle vehicle, double price, UserProfile seller) {
        this.vehicle = vehicle;
        this.price = price;
        this.seller = seller;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and setters
    public Vehicle getVehicle() {
        return vehicle;
    }

    public UserProfile getSeller() {
        return seller;
    }
} 