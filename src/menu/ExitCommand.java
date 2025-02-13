package src.menu;

public class ExitCommand implements MenuCommand {
    @Override
    public String getDescription() {
        return "Exit";
    }
    
    @Override
    public void execute() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
} 