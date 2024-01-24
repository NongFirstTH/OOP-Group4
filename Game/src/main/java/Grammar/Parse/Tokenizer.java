package Grammar.Parse;

public interface Tokenizer {
    boolean hasNextToken();

    String peek();

    String consume();
}
