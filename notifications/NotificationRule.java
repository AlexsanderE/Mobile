package notifications;

import listings.Listing;
import notifications.channels.NotificationChannel;
import search.Filter;

import java.util.List;

public record NotificationRule(
        List<Filter<Listing>> filters,
        NotificationChannel channel
) {}