package Grammar.Parse;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParsePlanTest {

    StringBuilder parseThenPrettyPrint(String plan) throws SyntaxError {
        StringBuilder s = new StringBuilder();
        new PlanParser(new PlanTokenizer(plan)).parse().prettyPrint(s, 0);
        return s;
    }

    void assertParseThenPrettyPrint(String result, String plan) throws SyntaxError {
        StringBuilder s = parseThenPrettyPrint(plan);
        assertEquals(result, s.toString());
    }

    @Test
    public void assignmentStatementTest() throws SyntaxError {
        assertParseThenPrettyPrint("x = 10\n", "x = 10");
        assertParseThenPrettyPrint("f2p = (deposit / 10)\n", "f2p = deposit/10");
    }

    @Test
    public void doneRelocateCommandTest() throws SyntaxError {
        assertParseThenPrettyPrint("done\n", "done");
        assertParseThenPrettyPrint("relocate\n", "relocate");
    }

    @Test
    public void moveCommandTest() throws SyntaxError {
        assertParseThenPrettyPrint("move up\n", "move up");
        assertParseThenPrettyPrint("move down\n", "move down");
        assertParseThenPrettyPrint("move upleft\n", "move upleft");
        assertParseThenPrettyPrint("move upright\n", "move upright");
        assertParseThenPrettyPrint("move downleft\n", "move downleft");
        assertParseThenPrettyPrint("move downright\n", "move downright");
    }

    @Test
    public void regionCommandTest() throws SyntaxError {
        assertParseThenPrettyPrint("invest 10\n", "invest 10");
        assertParseThenPrettyPrint("collect (deposit / 2)\n", "collect deposit/2");
    }

    @Test
    public void attackCommandTest() throws SyntaxError {
        assertParseThenPrettyPrint("shoot up 1\n", "shoot up 1");
        assertParseThenPrettyPrint("shoot upleft budget\n", "shoot upleft budget");
        assertParseThenPrettyPrint("shoot downright ((x ^ 2) - (2 * x))\n", "shoot downright x^2-2*x");
    }

    @Test
    public void commentTest() throws SyntaxError {
        assertParseThenPrettyPrint("invest 10\n", "invest 10 # blah");
        assertParseThenPrettyPrint("collect (deposit / 2)\n", "collect deposit/2 # blah blah");
        assertParseThenPrettyPrint("shoot upleft ((x ^ 2) - (2 * x))\n", "shoot upleft x^2-2*x # blah blah blah");
    }

    @Test
    public void blockStatementTest() throws SyntaxError {
        assertParseThenPrettyPrint("","{}");
        assertParseThenPrettyPrint("done\ndone\ndone\n","{done done done}");
    }

    @Test
    public void ifStatementTest() throws SyntaxError {
        assertParseThenPrettyPrint(
            """
                    if (0) then {
                    } else {
                    }
                    """,

            "if (0) then {} else {}"
        );

        assertParseThenPrettyPrint(
                """
                        if ((budget - cost)) then {
                        \tshoot downright cost
                        } else {
                        }
                        """,

                "if (budget - cost) then shoot downright cost else {}"
        );
    }

    @Test
    public void WhileStatementTest() throws SyntaxError {
        assertParseThenPrettyPrint(
                """
                        while (1) {
                        }
                        """,

                "while (1) {}"
        );

        assertParseThenPrettyPrint(
                """
                        while ((budget - cost)) {
                        \tshoot downright cost
                        }
                        """,

                "while (budget - cost) shoot downright cost"
        );
    }

    @Test
    public void throwTest() {
        assertThrows(SyntaxError.class, ()->parseThenPrettyPrint("cols = 10"));
        assertThrows(SyntaxError.class, ()->parseThenPrettyPrint("if = 5"));
        assertThrows(SyntaxError.class, ()->parseThenPrettyPrint("relocate 20"));
        assertThrows(SyntaxError.class, ()->parseThenPrettyPrint("move left"));
        assertThrows(SyntaxError.class, ()->parseThenPrettyPrint("invest (0"));
        assertThrows(SyntaxError.class, ()->parseThenPrettyPrint("{done"));
        assertThrows(SyntaxError.class, ()->parseThenPrettyPrint("if 0 else{}"));
    }

    @Test
    public void sampleConstructionPlanTest() throws SyntaxError {
        assertParseThenPrettyPrint(
                """
                        t = (t + 1)
                        m = 0
                        while (deposit) {
                        \tif ((deposit - 100)) then {
                        \t\tcollect (deposit / 4)
                        \t} else {
                        \t\tif ((budget - 25)) then {
                        \t\t\tinvest 25
                        \t\t} else {
                        \t\t}
                        \t}
                        \tif ((budget - 100)) then {
                        \t} else {
                        \t\tdone
                        \t}
                        \topponentLoc = opponent
                        \tif (((opponentLoc / 10) - 1)) then {
                        \t\tif (((opponentLoc % 10) - 5)) then {
                        \t\t\tmove downleft
                        \t\t} else {
                        \t\t\tif (((opponentLoc % 10) - 4)) then {
                        \t\t\t\tmove down
                        \t\t\t} else {
                        \t\t\t\tif (((opponentLoc % 10) - 3)) then {
                        \t\t\t\t\tmove downright
                        \t\t\t\t} else {
                        \t\t\t\t\tif (((opponentLoc % 10) - 2)) then {
                        \t\t\t\t\t\tmove upleft
                        \t\t\t\t\t} else {
                        \t\t\t\t\t\tif (((opponentLoc % 10) - 1)) then {
                        \t\t\t\t\t\t\tmove upright
                        \t\t\t\t\t\t} else {
                        \t\t\t\t\t\t\tmove up
                        \t\t\t\t\t\t}
                        \t\t\t\t\t}
                        \t\t\t\t}
                        \t\t\t}
                        \t\t}
                        \t} else {
                        \t\tif (opponentLoc) then {
                        \t\t\tif (((opponentLoc % 10) - 5)) then {
                        \t\t\t\tcost = (10 ^ ((nearby upleft % 100) + 1))
                        \t\t\t\tif ((budget - cost)) then {
                        \t\t\t\t\tshoot upleft cost
                        \t\t\t\t} else {
                        \t\t\t\t}
                        \t\t\t} else {
                        \t\t\t\tif (((opponentLoc % 10) - 4)) then {
                        \t\t\t\t\tcost = (10 ^ ((nearby downleft % 100) + 1))
                        \t\t\t\t\tif ((budget - cost)) then {
                        \t\t\t\t\t\tshoot downleft cost
                        \t\t\t\t\t} else {
                        \t\t\t\t\t}
                        \t\t\t\t} else {
                        \t\t\t\t\tif (((opponentLoc % 10) - 3)) then {
                        \t\t\t\t\t\tcost = (10 ^ ((nearby down % 100) + 1))
                        \t\t\t\t\t\tif ((budget - cost)) then {
                        \t\t\t\t\t\t\tshoot down cost
                        \t\t\t\t\t\t} else {
                        \t\t\t\t\t\t}
                        \t\t\t\t\t} else {
                        \t\t\t\t\t\tif (((opponentLoc % 10) - 2)) then {
                        \t\t\t\t\t\t\tcost = (10 ^ ((nearby downright % 100) + 1))
                        \t\t\t\t\t\t\tif ((budget - cost)) then {
                        \t\t\t\t\t\t\t\tshoot downright cost
                        \t\t\t\t\t\t\t} else {
                        \t\t\t\t\t\t\t}
                        \t\t\t\t\t\t} else {
                        \t\t\t\t\t\t\tif (((opponentLoc % 10) - 1)) then {
                        \t\t\t\t\t\t\t\tcost = (10 ^ ((nearby upright % 100) + 1))
                        \t\t\t\t\t\t\t\tif ((budget - cost)) then {
                        \t\t\t\t\t\t\t\t\tshoot upright cost
                        \t\t\t\t\t\t\t\t} else {
                        \t\t\t\t\t\t\t\t}
                        \t\t\t\t\t\t\t} else {
                        \t\t\t\t\t\t\t\tcost = (10 ^ ((nearby up % 100) + 1))
                        \t\t\t\t\t\t\t\tif ((budget - cost)) then {
                        \t\t\t\t\t\t\t\t\tshoot up cost
                        \t\t\t\t\t\t\t\t} else {
                        \t\t\t\t\t\t\t\t}
                        \t\t\t\t\t\t\t}
                        \t\t\t\t\t\t}
                        \t\t\t\t\t}
                        \t\t\t\t}
                        \t\t\t}
                        \t\t} else {
                        \t\t\tdir = (random % 6)
                        \t\t\tif ((dir - 4)) then {
                        \t\t\t\tmove upleft
                        \t\t\t} else {
                        \t\t\t\tif ((dir - 3)) then {
                        \t\t\t\t\tmove downleft
                        \t\t\t\t} else {
                        \t\t\t\t\tif ((dir - 2)) then {
                        \t\t\t\t\t\tmove down
                        \t\t\t\t\t} else {
                        \t\t\t\t\t\tif ((dir - 1)) then {
                        \t\t\t\t\t\t\tmove downright
                        \t\t\t\t\t\t} else {
                        \t\t\t\t\t\t\tif (dir) then {
                        \t\t\t\t\t\t\t\tmove upright
                        \t\t\t\t\t\t\t} else {
                        \t\t\t\t\t\t\t\tmove up
                        \t\t\t\t\t\t\t}
                        \t\t\t\t\t\t}
                        \t\t\t\t\t}
                        \t\t\t\t}
                        \t\t\t}
                        \t\t\tm = (m + 1)
                        \t\t}
                        \t}
                        }
                        if ((budget - 1)) then {
                        \tinvest 1
                        } else {
                        }
                        """,

                """
                        t = t + 1  # keeping track of the turn number
                        m = 0  # number of random moves
                        while (deposit) { # still our region
                          if (deposit - 100)
                          then collect (deposit / 4)  # collect 1/4 of available deposit
                          else if (budget - 25) then invest 25
                          else {}
                          if (budget - 100) then {} else done  # too poor to do anything else
                          opponentLoc = opponent
                          if (opponentLoc / 10 - 1)
                          then  # opponent afar
                            if (opponentLoc % 10 - 5) then move downleft
                            else if (opponentLoc % 10 - 4) then move down
                            else if (opponentLoc % 10 - 3) then move downright
                            else if (opponentLoc % 10 - 2) then move upleft
                            else if (opponentLoc % 10 - 1) then move upright
                            else move up
                          else if (opponentLoc)
                          then  # opponent adjacent to city crew
                            if (opponentLoc % 10 - 5) then {
                              cost = 10 ^ (nearby upleft % 100 + 1)
                              if (budget - cost) then shoot upleft cost else {}
                            }
                            else if (opponentLoc % 10 - 4) then {
                              cost = 10 ^ (nearby downleft % 100 + 1)
                              if (budget - cost) then shoot downleft cost else {}
                            }
                            else if (opponentLoc % 10 - 3) then {
                              cost = 10 ^ (nearby down % 100 + 1)
                              if (budget - cost) then shoot down cost else {}
                            }
                            else if (opponentLoc % 10 - 2) then {
                              cost = 10 ^ (nearby downright % 100 + 1)
                              if (budget - cost) then shoot downright cost else {}
                            }
                            else if (opponentLoc % 10 - 1) then {
                              cost = 10 ^ (nearby upright % 100 + 1)
                              if (budget - cost) then shoot upright cost else {}
                            }
                            else {
                              cost = 10 ^ (nearby up % 100 + 1)
                              if (budget - cost) then shoot up cost else {}
                            }
                          else {  # no visible opponent; move in a random direction
                            dir = random % 6
                            if (dir - 4) then move upleft
                            else if (dir - 3) then move downleft
                            else if (dir - 2) then move down
                            else if (dir - 1) then move downright
                            else if (dir) then move upright
                            else move up
                            m = m + 1
                          }
                        }  # end while
                        # city crew on a region belonging to nobody, so claim it
                        if (budget - 1) then invest 1 else {}
                        \s"""
        );
    }
}