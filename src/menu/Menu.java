package src.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final List<MenuCommand> commands;
    private final Scanner scanner;
    private boolean loggedOut = false;
    
    public Menu(Scanner scanner) {
        this.commands = new ArrayList<>();
        this.scanner = scanner;
    }
    
    public void addCommand(MenuCommand command) {
        commands.add(command);
    }
    
    public void setLoggedOut(boolean loggedOut) {
        this.loggedOut = loggedOut;
    }
    
    public void show() {
        while (!loggedOut) {
            System.out.println("\nPlease select an option:");
            for (int i = 0; i < commands.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, commands.get(i).getDescription());
            }
            System.out.print("Choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice > 0 && choice <= commands.size()) {
                    commands.get(choice - 1).execute();
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
} 