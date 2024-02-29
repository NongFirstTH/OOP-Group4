package Grammar.Parse;

import java.util.NoSuchElementException;

interface Tokenizer {
    /**
     * Returns true if there is
     * more token
     */
    boolean hasNextToken();

    /**
     * Returns the next token
     * in the input stream.
     */
    String peek();

    /**
     * Returns true if
     * the next token (if any) is s.
     */
    boolean peek(String s);

    /**
     * Consumes the next token
     * from the input stream
     * and returns it.
     * effects: removes the next token
     * from the input stream
     */
    String consume() throws SyntaxError;

    /**
     * Consumes the next token if it is s.
     * Throws SyntaxError otherwise.
     * effects: removes the next token
     * from input stream if it is s
     */
    void consume(String s) throws SyntaxError;
}

public class PlanTokenizer implements Tokenizer{
    private final String src;
    private String next;
    private int pos;

    public PlanTokenizer(String src) throws SyntaxError {
        this.src = src;  pos = 0;
        computeNext();
    }

    @Override
    public boolean hasNextToken(){
        return next != null;
    }

    private void checkNextToken() {
        if (!hasNextToken())
            throw new NoSuchElementException("no more tokens");
    }

    @Override
    public String peek() {
        checkNextToken();
        return next;
    }

    @Override
    public String consume() throws SyntaxError {
        checkNextToken();
        String result = next;
        computeNext();
        return result;
    }

    @Override
    public boolean peek(String s) {
        if (!hasNextToken()) return false;
        return peek().equals(s);
    }

    @Override
    public void consume(String s) throws SyntaxError {
        if (peek(s))
            consume();
        else
            throw new SyntaxError(s + " expected");
    }

    private void computeNext() throws SyntaxError {
        StringBuilder s = new StringBuilder();

        while (pos < src.length() && isIgnore(src.charAt(pos))) pos++;  // ignore whitespace

        if (pos < src.length() && isComment(src.charAt(pos))) {
            while (pos < src.length() && !(src.charAt(pos)=='\n')) pos++;
            computeNext();
            return;
        }

        if (pos >= src.length()){
            next = null;  return;
        }  // no more tokens

        char c = src.charAt(pos);

        if (isCharacter(c)) {
            s.append(c);
            for (pos++; pos < src.length() && isAlphanumericCharacters(src.charAt(pos)); pos++)
                s.append(src.charAt(pos));
        }
        else if (isDigit(c)) {  // start of number
            s.append(c);
            for (pos++; pos < src.length() && isDigit(src.charAt(pos)); pos++)
                s.append(src.charAt(pos));
        }
        else if (isParentheses(c)) {
            s.append(c);
            pos++;
        }
        else if (isOperator(c)){
            s.append(c);
            pos++;
        }else throw new SyntaxError("unknown character: " + c);
        next = s.toString();
    }

    private boolean isAlphanumericCharacters(char c) {
        return isCharacter(c)||isDigit(c)||c=='_';
    }

    private boolean isComment(char character) {
        return character == '#';
    }

    private boolean isCharacter(char character) {
        return Character.toString(character).matches("[a-zA-Z]");
    }

    private boolean isParentheses(char character) {
        return Character.toString(character).matches("[(){}]");
    }

    private boolean isDigit(char character){
        return character>='0' && character<='9';
    }

    private boolean isIgnore(char character) {
        return character == ' ' || character == '\t' || character == '\n';
    }

    private boolean isOperator(char character) {
        return Character.toString(character).matches("[+\\-*/%^=]");
    }

}

