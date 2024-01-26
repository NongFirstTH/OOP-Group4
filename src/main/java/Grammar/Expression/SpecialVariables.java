package Grammar.Expression;

import java.util.Map;

record SpecialVariables(String name) implements Expression {
    public int eval(Map<String, Integer> bindings){
        return 0;
    }
}
