package src.listings;
import java.util.List;

import src.search.SearchCriteria;

import java.util.ArrayList;

public class UserProfile {
    private String username;
    private String email;
    private String phone;
    private List<Listing> listings;
    private List<SearchCriteria> savedSearches;
    
    public UserProfile(String username, String email, String phone) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.listings = new ArrayList<>();
        this.savedSearches = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }

    public List<SearchCriteria> getSavedSearches() {
        return savedSearches;
    }

    public String getUsername() {
        return username;
    }
} 