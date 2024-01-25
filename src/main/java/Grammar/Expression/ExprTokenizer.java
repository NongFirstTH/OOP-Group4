package Grammar.Expression;

import Grammar.Parse.Tokenizer;

public class ExprTokenizer implements Tokenizer {
    private String src, next;
    private int pos;
    public ExprTokenizer(String src)
    public boolean hasNextToken()
    public void checkNextToken()
    public String peek()
    public String consume()
    private void computeNext()
    public boolean peek(String s)
    public void consume(String s)

}
