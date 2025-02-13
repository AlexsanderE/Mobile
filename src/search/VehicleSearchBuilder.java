package src.search;

import src.vehicles.Vehicle;

public class VehicleSearchBuilder {
    public static SearchCriteria byBrand(String brand) {
        return new VehicleSpecification<String>(
            Vehicle::getBrand,
            brand,
            String::equalsIgnoreCase
        );
    }
    
    public static SearchCriteria byModel(String model) {
        return new VehicleSpecification<>(
            Vehicle::getModel,
            model,
            String::equalsIgnoreCase
        );
    }
    
    public static SearchCriteria byYear(int year) {
        return new VehicleSpecification<Integer>(
            Vehicle::getYear,
            year,
            (a, b) -> a.equals(b)
        );
    }
    
    public static SearchCriteria byPriceRange(double min, double max) {
        return vehicle -> {
            double price = vehicle.getPrice();
            return price >= min && price <= max;
        };
    }
} 