package notifications.channels;

public interface NotificationChannel {
    void notify(String title, String message);
}