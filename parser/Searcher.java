package parser;

import listings.Listing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public interface Searcher {
    List<Listing> search(List<Listing> listings, String query);

    default List<Listing> searchAnd(List<Listing> listings, String... queries) {
        List<Listing> result = listings;
        for (String query : queries) {
            result = search(result, query);
        }
        return result;
    }

    default List<Listing> searchOr(List<Listing> listings, String... queries) {
        List<Listing> result = new ArrayList<>(); // Use a mutable list
        for (String query : queries) {
            List<Listing> queryResult = search(listings, query);
            result = mergeResults(result, queryResult);
        }
        return result;
    }

    private List<Listing> mergeResults(List<Listing> list1, List<Listing> list2) {
        return Stream.concat(list1.stream(), list2.stream())
                .distinct()
                .toList();
    }
}

//package parser;
//
//import listings.Listing;
//
//import java.util.List;
//
//public interface Searcher {
//    List<Listing> search(List<Listing> listings, String query);
//}