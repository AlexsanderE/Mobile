package src.users;

import src.users.UserProfile;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserProfileManager {
    private static final String USERS_FILE = "users.dat";
    private Map<String, UserProfile> users;
    
    public UserProfileManager() {
        this.users = new HashMap<>();
        loadUsers();
    }
    
    public void saveUser(UserProfile user) {
        users.put(user.getUsername(), user);
        saveUsers();
    }
    
    public UserProfile getUser(String username) {
        return users.get(username);
    }
    
    public boolean userExists(String username) {
        return users.containsKey(username);
    }
    
    private void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            users = (Map<String, UserProfile>) ois.readObject();
        } catch (FileNotFoundException e) {
            users = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading users: " + e.getMessage());
            users = new HashMap<>();
        }
    }
    
    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }
} 