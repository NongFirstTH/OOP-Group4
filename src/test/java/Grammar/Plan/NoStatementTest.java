package Grammar.Plan;

import Grammar.Expression.EvalError;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class NoStatementTest {
    @Test
    public void testEval() throws EvalError {
        assertFalse(new NoStatement().eval(null));
    }
}