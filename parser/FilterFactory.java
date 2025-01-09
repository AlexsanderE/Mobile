package parser;

import listings.Listing;
import search.ExactValueFilter;
import search.Filter;
import search.RangeFilter;
import vehicles.Car;

import java.util.function.Function;

public class FilterFactory {
    public Filter<Listing> createExactFilter(String field, String value) {
        return switch (field) {
            case "brand" -> createCarFieldFilter(Car::brand, value);
            case "model" -> createCarFieldFilter(Car::model, value);
            case "year" -> createCarFieldFilter(Car::year, Integer.parseInt(value));
            case "isManual" -> createCarFieldFilter(Car::isManual, Boolean.parseBoolean(value));
            default -> throw new IllegalArgumentException("Unknown field: " + field);
        };
    }

    public Filter<Listing> createRangeFilter(String field, String value, boolean isGreaterThan) {
        if (!field.equals("year")) {
            throw new IllegalArgumentException("Range filters only supported for year field");
        }

        int yearValue = Integer.parseInt(value);
        return new RangeFilter<>(
                listing -> listing.car().year(),
                isGreaterThan ? yearValue : Integer.MIN_VALUE,
                isGreaterThan ? Integer.MAX_VALUE : yearValue
        );
    }

    private <V> Filter<Listing> createCarFieldFilter(Function<Car, V> getter, V value) {
        return new ExactValueFilter<>(
                listing -> getter.apply(listing.car()),
                value
        );
    }
}