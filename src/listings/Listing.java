package src.listings;

import java.io.Serializable;
import java.time.LocalDateTime;
import src.vehicles.Vehicle;
import src.users.UserProfile;

public class Listing implements Serializable {
    private static final long serialVersionUID = 1L;
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
    
    public Vehicle getVehicle() {
        return vehicle;
    }

    public UserProfile getSeller() {
        return seller;
    }
} 