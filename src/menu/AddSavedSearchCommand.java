package src.menu;

import src.users.UserProfile;
import src.search.SearchCriteria;
import src.search.VehicleSearchBuilder;
import java.util.Scanner;

public class AddSavedSearchCommand implements MenuCommand {
    private final Scanner scanner;
    private final UserProfile user;
    
    public AddSavedSearchCommand(Scanner scanner, UserProfile user) {
        this.scanner = scanner;
        this.user = user;
    }
    
    @Override
    public String getDescription() {
        return "Add saved search";
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== Add Saved Search ===");
        SearchCriteria criteria = buildCombinedSearch();
        user.getSavedSearches().add(criteria);
        System.out.println("Search criteria saved! You'll be notified of matching listings.");
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
} 