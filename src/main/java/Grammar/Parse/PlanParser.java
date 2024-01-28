package Grammar.Parse;

import Grammar.Expression.*;
import Grammar.Plan.*;

public class PlanParser implements Parser<Plan> {
    private final PlanTokenizer tkz;

    public PlanParser(PlanTokenizer tkz) {
        this.tkz = tkz;
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
            p = new BinaryPlan(p, parseStatement());
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
        String identifier = tkz.consume();
        tkz.consume("=");
        return new AssignmentStatement(identifier, parseExpression());
    }

    private Plan parseActionCommand() throws SyntaxError {
        String peek = tkz.consume();
        return switch (peek) {
            case "done" -> new Done();
            case "relocate" -> new Relocate();
            case "move" -> new MoveCommand(tkz.consume());
            case "shoot" -> new AttackCommand(tkz.consume(), parseExpression());
            default -> new RegionCommand(peek, parseExpression());
        };
    }

    private Plan parseBlockStatement() throws SyntaxError {
        tkz.consume("{");
        Plan p = new NoStatement();
        while (tkz.hasNextToken()&&!tkz.peek().equals("}")) {
            p = new BinaryPlan(p, parseStatement());
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
        return  new ExpressionParser(tkz).parse();
    }


}
