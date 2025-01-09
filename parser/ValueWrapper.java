package parser;

import listings.Listing;
import search.Filter;

public class ValueWrapper implements Filter<Listing> {
    private final String value;

    public ValueWrapper(String value) {
        this.value = value;
    }

    @Override
    public boolean matches(Listing item) {
        throw new UnsupportedOperationException("ValueWrapper is just storing values");
    }

    @Override
    public String toString() {
        return value;
    }
}