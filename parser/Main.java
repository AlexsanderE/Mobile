package parser;

public class Main {
    public static void main(String[] args) {
        QueryParser parser = new QueryParser();
        Searcher searcher = new QuerySearcher(parser);

        QueryTester tester = new QueryTester();
        tester.test(searcher);
    }
}