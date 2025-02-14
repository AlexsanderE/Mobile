package src.listings;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListingManager {
    private static final String LISTINGS_FILE = "listings.dat";
    private List<Listing> listings;
    
    public ListingManager() {
        this.listings = new ArrayList<>();
        loadListings();
    }
    
    public void saveListing(Listing listing) {
        listings.add(listing);
        saveListings();
    }
    
    public List<Listing> getAllListings() {
        return new ArrayList<>(listings);
    }
    
    private void loadListings() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(LISTINGS_FILE))) {
            listings = (List<Listing>) ois.readObject();
        } catch (FileNotFoundException e) {
            listings = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading listings: " + e.getMessage());
            listings = new ArrayList<>();
        }
    }
    
    private void saveListings() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(LISTINGS_FILE))) {
            oos.writeObject(listings);
        } catch (IOException e) {
            System.err.println("Error saving listings: " + e.getMessage());
        }
    }
} 