package src.search;

import src.vehicles.Vehicle;

public interface SearchCriteria {
    boolean matches(Vehicle vehicle);
    
    default SearchCriteria and(SearchCriteria other) {
        return vehicle -> this.matches(vehicle) && other.matches(vehicle);
    }
    
    default SearchCriteria or(SearchCriteria other) {
        return vehicle -> this.matches(vehicle) || other.matches(vehicle);
    }
} 