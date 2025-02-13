package src.menu;

import src.listings.Listing;
import src.listings.UserProfile;
import src.listings.VehicleMarketplace;
import src.vehicles.Vehicle;
import src.vehicles.VehicleFactory;
import java.util.Scanner;

public class AddListingCommand implements MenuCommand {
    private final Scanner scanner;
    private final VehicleMarketplace marketplace;
    private final VehicleFactory factory;
    private final UserProfile currentUser;
    
    public AddListingCommand(Scanner scanner, VehicleMarketplace marketplace, 
                           VehicleFactory factory, UserProfile currentUser) {
        this.scanner = scanner;
        this.marketplace = marketplace;
        this.factory = factory;
        this.currentUser = currentUser;
    }
    
    @Override
    public String getDescription() {
        return "Add new vehicle listing";
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== Add New Listing ===");
        
        String type;
        while (true) {
            System.out.print("Vehicle type (car/truck): ");
            type = scanner.nextLine().toLowerCase();
            if (type.equals("car") || type.equals("truck")) {
                break;
            }
            System.out.println("Invalid type. Please enter 'car' or 'truck'.");
        }
        
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        
        System.out.print("Model: ");
        String model = scanner.nextLine();
        
        int year;
        while (true) {
            try {
                System.out.print("Year: ");
                year = Integer.parseInt(scanner.nextLine());
                if (year >= 1900 && year <= 2024) {
                    break;
                }
                System.out.println("Please enter a valid year between 1900 and 2024.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        
        double price;
        while (true) {
            try {
                System.out.print("Price: $");
                price = Double.parseDouble(scanner.nextLine());
                if (price > 0) {
                    break;
                }
                System.out.println("Price must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        
        try {
            Vehicle vehicle = factory.createVehicle(type, brand, model, year);
            vehicle.setPrice(price);
            marketplace.addListing(new Listing(vehicle, price, currentUser));
            System.out.println("Listing added successfully!");
        } catch (Exception e) {
            System.out.println("Error creating listing: " + e.getMessage());
        }
    }
} 