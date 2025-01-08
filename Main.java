import vehicles.Car;
import search.*;
import listings.*;
import notifications.*;
import notifications.channels.*;
import notifications.external.*;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Initialize services
        ListingStorage listingStorage = new ListingStorage();
        NotificationService notificationService = new NotificationService();
        ListingService listingService = new ListingService(listingStorage, notificationService);

        EmailNotifier emailNotifier = new EmailNotifier();
        SmsNotifier smsNotifier = new SmsNotifier();
        PigeonNotifier pigeonNotifier = new PigeonNotifier();

        NotificationChannel emailChannel = new EmailNotificationChannel(emailNotifier, "Aleksandar@example.com");
        NotificationChannel smsChannel = new SmsNotificationChannel(smsNotifier, "+1234567890");
        NotificationChannel pigeonChannel = new PigeonNotificationChannel(pigeonNotifier, "123 Street");

        FieldExtractor<Listing, String> brandExtractor = listing -> listing.car().brand();
        FieldExtractor<Listing, Integer> priceExtractor = Listing::price;
        FieldExtractor<Listing, Integer> yearExtractor = listing -> listing.car().year();

        Filter<Listing> mitsubishiFilter = new CaseInsensitiveFilter<>(brandExtractor, "Mitsubishi");
        Filter<Listing> priceRangeFilter = new RangeFilter<>(priceExtractor, 5000, 15000);
        Filter<Listing> yearRangeFilter = new RangeFilter<>(yearExtractor, 2015, 2023);

        List<Filter<Listing>> carNotificationFilters = new ArrayList<>();
        carNotificationFilters.add(priceRangeFilter);
        carNotificationFilters.add(yearRangeFilter);

        notificationService.subscribe(new NotificationRule(carNotificationFilters, emailChannel));
        notificationService.subscribe(new NotificationRule(carNotificationFilters, smsChannel));

        System.out.println("Adding listings:\n");

        Car mitsubishi = new Car("Mitsubishi", "Lancer", 2018, true);
        Listing listing1 = new Listing(mitsubishi, 12000);
        listingService.addListing(listing1);

        Car opel = new Car("Opel", "Astra", 2016, false);
        Listing listing2 = new Listing(opel, 8000);
        listingService.addListing(listing2);

        Car bmw = new Car("BMW", "5 Series", 2020, false);
        Listing listing3 = new Listing(bmw, 25000);
        listingService.addListing(listing3);

        System.out.println("\nFiltering:");

        System.out.println("\nAll cars between 5000-15000$ and years 2015-2023:");
        for (Listing listing : listingStorage.getListings()) {
            if (priceRangeFilter.matches(listing) && yearRangeFilter.matches(listing)) {
                System.out.println("- " + listing.car().brand() + " " + listing.car().model() +
                        "\n  Price: $" + listing.price() +
                        "\n  Year: " + listing.car().year() +
                        "\n  Transmission: " + (listing.car().isManual() ? "Manual" : "Automatic") + "\n");
            }
        }

        System.out.println("\nAll Mitsubishi cars:");
        for (Listing listing : listingStorage.getListings()) {
            if (mitsubishiFilter.matches(listing)) {
                System.out.println("- " + listing.car().brand() + " " + listing.car().model() +
                        "\n  Price: $" + listing.price() +
                        "\n  Year: " + listing.car().year() +
                        "\n  Transmission: " + (listing.car().isManual() ? "Manual" : "Automatic") + "\n");
            }
        }
    }
}