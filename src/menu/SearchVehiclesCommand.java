package src.menu;

import src.listings.VehicleMarketplace;
import src.listings.Listing;
import src.search.SearchCriteria;
import src.search.VehicleSearchBuilder;
import src.vehicles.Vehicle;
import java.util.List;
import java.util.Scanner;

public class SearchVehiclesCommand implements MenuCommand {
    private final Scanner scanner;
    private final VehicleMarketplace marketplace;
    
    public SearchVehiclesCommand(Scanner scanner, VehicleMarketplace marketplace) {
        this.scanner = scanner;
        this.marketplace = marketplace;
    }
    
    @Override
    public String getDescription() {
        return "Search vehicles";
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== Search Vehicles ===");
        System.out.println("1. Search by brand");
        System.out.println("2. Search by price range");
        System.out.println("3. Search by year");
        System.out.println("4. Combined search");
        System.out.print("Choice: ");
        
        SearchCriteria criteria = null;
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Enter brand: ");
                    criteria = VehicleSearchBuilder.byBrand(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter minimum price: $");
                    double min = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter maximum price: $");
                    double max = Double.parseDouble(scanner.nextLine());
                    criteria = VehicleSearchBuilder.byPriceRange(min, max);
                    break;
                case 3:
                    System.out.print("Enter year: ");
                    criteria = VehicleSearchBuilder.byYear(Integer.parseInt(scanner.nextLine()));
                    break;
                case 4:
                    criteria = buildCombinedSearch();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }
            
            printSearchResults(marketplace.search(criteria));
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numbers.");
        }
    }
    
    private SearchCriteria buildCombinedSearch() {
        SearchCriteria criteria = null;
        
        System.out.print("Enter brand (or press enter to skip): ");
        String brand = scanner.nextLine();
        if (!brand.isEmpty()) {
            criteria = VehicleSearchBuilder.byBrand(brand);
        }
        
        System.out.print("Enter model (or press enter to skip): ");
        String model = scanner.nextLine();
        if (!model.isEmpty()) {
            SearchCriteria modelCriteria = VehicleSearchBuilder.byModel(model);
            criteria = (criteria == null) ? modelCriteria : criteria.and(modelCriteria);
        }
        
        System.out.print("Enter year (or 0 to skip): ");
        String yearStr = scanner.nextLine();
        if (!yearStr.isEmpty() && !yearStr.equals("0")) {
            SearchCriteria yearCriteria = VehicleSearchBuilder.byYear(Integer.parseInt(yearStr));
            criteria = (criteria == null) ? yearCriteria : criteria.and(yearCriteria);
        }
        
        return criteria;
    }
    
    private void printSearchResults(List<Listing> listings) {
        if (listings.isEmpty()) {
            System.out.println("No matches found");
            return;
        }
        
        System.out.println("\nSearch Results:");
        for (Listing listing : listings) {
            Vehicle vehicle = listing.getVehicle();
            System.out.printf("%s %s %d - $%.2f%n",
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getPrice());
        }
    }
} 