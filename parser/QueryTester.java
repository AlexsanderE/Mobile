package parser;

import listings.Listing;
import vehicles.Car;

import java.util.List;
import java.util.Map;

public class QueryTester {
    public static final Listing BMW_X5 = new Listing(new Car("bmw", "x5", 2000, true), 10000);
    public static final Listing BMW_E60 = new Listing(new Car("bmw", "e60", 2005, false), 7000);
    public static final Listing A4_OLD = new Listing(new Car("audi", "a4", 2001, false), 5000);
    public static final Listing A4_NEW = new Listing(new Car("audi", "a4", 2003, false), 5000);
    public final static List<Listing> EXAMPLE_LISTINGS = List.of(
            BMW_X5,
            BMW_E60,
            A4_OLD,
            A4_NEW
    );

    public final static Map<String, List<Listing>> QUERY_TO_EXPECTED_RESULTS = Map.of(
            "brand = 'bmw'", List.of(BMW_X5, BMW_E60),
            "year < 2002", List.of(BMW_X5, A4_OLD),
            "year = 2005", List.of(BMW_E60),
            "brand = 'bmw' | model = 'a4'", List.of(BMW_X5, BMW_E60, A4_OLD, A4_NEW),
            "( brand = 'bmw' & model = 'x5' ) | ( brand = 'audi' & model = 'a4' & year > 2002 )", List.of(BMW_X5, A4_NEW)
    );

    public void test(Searcher searcher) {
        QUERY_TO_EXPECTED_RESULTS.forEach((query, expected) -> test(searcher, query, expected));
    }

    private void test(Searcher searcher, String query, List<Listing> expected) {
        System.out.println();
        try {
            List<Listing> result = searcher.search(EXAMPLE_LISTINGS, query);
            if (result.equals(expected)) {
                System.out.println("Query: " + query + " passed :)");
            } else {
                System.out.println("Query: " + query + " failed :(");
                System.out.println("Expected: " + expected);
                System.out.println("Got: " + result);
            }
        } catch (Exception e) {
            System.out.println("Query: " + query + " failed with exception :(");
            System.out.println("Exception: " + e);
        }
    }
}