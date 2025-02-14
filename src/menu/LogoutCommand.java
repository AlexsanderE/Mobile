package src.menu;

public class LogoutCommand implements MenuCommand {
    private final Menu menu;
    
    public LogoutCommand(Menu menu) {
        this.menu = menu;
    }
    
    @Override
    public String getDescription() {
        return "Logout";
    }
    
    @Override
    public void execute() {
        System.out.println("Logging out...");
        menu.setLoggedOut(true);
    }
} 