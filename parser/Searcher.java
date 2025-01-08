package parser;

import listings.Listing;

import java.util.List;

public interface Searcher {
    List<Listing> search(List<Listing> listings, String query);
}