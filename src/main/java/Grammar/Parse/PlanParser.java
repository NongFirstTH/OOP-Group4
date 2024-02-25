package Grammar.Parse;

import Grammar.Expression.*;
import Grammar.Plan.*;

public class PlanParser implements Parser<Plan> {
    private final PlanTokenizer tkz;
    private final ExpressionParser exprParser;
    private final DirectionParser dirParser;

    public PlanParser(PlanTokenizer tkz) {
        this.tkz = tkz;
        exprParser = new ExpressionParser(tkz);
        dirParser = new DirectionParser(tkz);
    }
    public Plan parse() throws SyntaxError {
        // begin parsing at start symbol
        Plan v = parsePlan();
        // reject if there is remaining token
        if (tkz.hasNextToken())
            throw new SyntaxError("leftover token");

        return v;
    }

    private Plan parsePlan() throws SyntaxError {
        Plan p = parseStatement();
        while (tkz.hasNextToken()) {
            p = new StatementPair(p, parseStatement());
        }
        return p;
    }

    private Plan parseStatement() throws SyntaxError {
        String peek = tkz.peek();
        return switch (peek) {
            case "{" -> parseBlockStatement();
            case "if" -> parseIfStatement();
            case "while" -> parseWhileStatement();
            default -> parseCommand();
        };
    }

    private Plan parseCommand() throws SyntaxError {
        String peek = tkz.peek();
        if (peek.equals("done")||peek.equals("relocate")||peek.equals("move")||peek.equals("shoot")||peek.equals("invest")||peek.equals("collect")) {
            return parseActionCommand();
        }
        return parseAssignmentStatement();
    }

    private Plan parseAssignmentStatement() throws SyntaxError {
        String peek = tkz.consume();
        boolean isSpecialVariable =
        peek.equals("rows") ||
                peek.equals("cols") ||
                peek.equals("currow") ||
                peek.equals("curcol") ||
                peek.equals("budget") ||
                peek.equals("deposit") ||
                peek.equals("int") ||
                peek.equals("maxdeposit") ||
                peek.equals("random");
        if(isSpecialVariable) throw new SyntaxError(peek+" is special variable");
        tkz.consume("=");
        return new AssignmentStatement(peek, parseExpression());
    }

    private Plan parseActionCommand() throws SyntaxError {
        String peek = tkz.consume();
        return switch (peek) {
            case "done" -> new Done();
            case "relocate" -> new Relocate();
            case "move" -> new MoveCommand(dirParser.parse());
            case "shoot" -> new AttackCommand(dirParser.parse(), parseExpression());
            default -> new RegionCommand(peek, parseExpression());
        };
    }

    private Plan parseBlockStatement() throws SyntaxError {
        tkz.consume("{");
        Plan p = new NoStatement();
        while (tkz.hasNextToken()&&!tkz.peek().equals("}")) {
            p = new StatementPair(p, parseStatement());
        }
        tkz.consume("}");
        return p;
    }

    private Plan parseIfStatement() throws SyntaxError {
        tkz.consume("if");
        tkz.consume("(");
        Expression expr = parseExpression();
        tkz.consume(")");
        tkz.consume("then");
        Plan p = parseStatement();
        tkz.consume("else");
        return new IfStatement(expr, p, parseStatement());
    }

    private Plan parseWhileStatement() throws SyntaxError {
        tkz.consume("while");
        tkz.consume("(");
        Expression expr = parseExpression();
        tkz.consume(")");
        return new WhileStatement(expr, parseStatement());
    }

    private Expression parseExpression() throws SyntaxError {
        return exprParser.parse();
    }


}
