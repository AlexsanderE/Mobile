package parser;

import listings.Listing;
import search.Filter;
import search.composite.AndFilter;
import search.composite.OrFilter;

import java.util.List;
import java.util.Stack;

public class QuerySearcher implements Searcher {
    private final QueryParser queryParser;
    private final FilterFactory filterFactory;

    public QuerySearcher(QueryParser queryParser) {
        this.queryParser = queryParser;
        this.filterFactory = new FilterFactory();
    }

    @Override
    public List<Listing> search(List<Listing> listings, String query) {
        List<String> tokens = queryParser.toPolishNotation(query);
        Filter<Listing> filter = buildFilter(tokens);
        return listings.stream()
                .filter(filter::matches)
                .toList();
    }

    private Filter<Listing> buildFilter(List<String> tokens) {
        Stack<Filter<Listing>> stack = new Stack<>();

        for (String token : tokens) {
            switch (token) {
                case "&" -> {
                    Filter<Listing> right = stack.pop();
                    Filter<Listing> left = stack.pop();
                    stack.push(new AndFilter<>(left, right));
                }
                case "|" -> {
                    Filter<Listing> right = stack.pop();
                    Filter<Listing> left = stack.pop();
                    stack.push(new OrFilter<>(left, right));
                }
                case "=" -> {
                    String value = stack.pop().toString();
                    String field = stack.pop().toString();
                    value = value.replace("'", "");
                    stack.push(filterFactory.createExactFilter(field, value));
                }
                case ">" -> {
                    String value = stack.pop().toString();
                    String field = stack.pop().toString();
                    stack.push(filterFactory.createRangeFilter(field, value, true));
                }
                case "<" -> {
                    String value = stack.pop().toString();
                    String field = stack.pop().toString();
                    stack.push(filterFactory.createRangeFilter(field, value, false));
                }
                default -> stack.push(new ValueWrapper(token));
            }
        }

        return stack.pop();
    }
}