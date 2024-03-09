package com.websocket.demo.GamePlay;

import com.websocket.demo.Grammar.Expression.EvalError;
import com.websocket.demo.Grammar.Parse.PlanParser;
import com.websocket.demo.Grammar.Parse.PlanTokenizer;
import com.websocket.demo.Grammar.Parse.SyntaxError;
import com.websocket.demo.Grammar.Plan.Plan;
import com.websocket.demo.GamePlay.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private void gameTest() throws SyntaxError, EvalError {
        Game g =  new Game(10, 10);

        String s1 = """
                        t = t + 1  # keeping track of the turn number
                        m = 0  # number of random moves
                        if(deposit-1) then collect deposit-1  else {}
                        while (budget) { # still our region
                          opponentLoc = opponent
                          if (opponentLoc / 10 - 1)
                          then  # opponent afar
                            if (opponentLoc % 10 - 5) then move upleft
                            else if (opponentLoc % 10 - 4) then move downleft
                            else if (opponentLoc % 10 - 3) then move down
                            else if (opponentLoc % 10 - 2) then move downright
                            else if (opponentLoc % 10 - 1) then move upright
                            else move up
                          else if (opponentLoc)
                          then  # opponent adjacent to city crew
                            if (opponentLoc % 10 - 5) then {
                              cost = 10 ^ (nearby upleft % 100 + 1)
                              if (budget - cost) then shoot upleft cost else {shoot upleft budget}
                            }
                            else if (opponentLoc % 10 - 4) then {
                              cost = 10 ^ (nearby downleft % 100 + 1)
                              if (budget - cost) then shoot downleft cost else {shoot downleft budget}
                            }
                            else if (opponentLoc % 10 - 3) then {
                              cost = 10 ^ (nearby down % 100 + 1)
                              if (budget - cost) then shoot down cost else {shoot down budget}
                            }
                            else if (opponentLoc % 10 - 2) then {
                              cost = 10 ^ (nearby downright % 100 + 1)
                              if (budget - cost) then shoot downright cost else {shoot downright budget}
                            }
                            else if (opponentLoc % 10 - 1) then {
                              cost = 10 ^ (nearby upright % 100 + 1)
                              if (budget - cost) then shoot upright cost else {shoot upright budget}
                            }
                            else {
                              cost = 10 ^ (nearby up % 100 + 1)
                              if (budget - cost) then shoot up cost else {shoot up budget}
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
                        #if (budget - 1) then invest 1 else {}
                        \s""";

        String s2 = "done";
//"""
//                while(budget) {
//                    if(maxdeposit-deposit-interest)
//                    then if (budget-maxdeposit+deposit+interest)
//                         then invest maxdeposit-deposit-interest
//                         else invest budget
//                    else collect interest
//                    dir = random
//                    if(dir%6-4)
//                    then move up
//                    else if(dir%6-3)
//                         then move upright
//                         else if(dir%6-2)
//                              then move downright
//                              else if(dir%6-1)
//                                   then move down
//                                   else if(dir%6)
//                                        then move downleft
//                                        else move upleft
//                }
//                """;

        Plan p1 = new PlanParser(new PlanTokenizer(s1)).parse();
        Plan p2 = new PlanParser(new PlanTokenizer(s2)).parse();

        g.addPlayer("first");
        g.devisePlan(0, p1);

        assertEquals("first", g.getPlayer().getName());
        int row1 = g.getPlayer().getRow();
        int col1 = g.getPlayer().getCol();


        g.addPlayer("knack");
        g.devisePlan(1, p2);
//        g.addPlayer("knack", p1);

        assertEquals("first", g.getPlayer().getName());


        g.nextTurn();

        assertEquals("knack", g.getPlayer().getName());
        assertFalse(row1==g.getPlayer().getRow()&&col1==g.getPlayer().getCol());

//        g.nextTurn();
//        g.executePlan();
//        g.nextTurn();
        while (g.executePlan()) {
            g.nextTurn();
        }

        assertEquals("first", g.getPlayer().getName());
    }

    @Test
    public void loop() throws SyntaxError, EvalError {
        for (int i=0;i<1000;i++) {
            System.out.println(i);
            gameTest();
        }
    }
    private void gameTest2() throws SyntaxError, EvalError {
        Game g =  new Game(10, 10);

        String s1 = """
                        t = t + 1  # keeping track of the turn number
                        m = 0  # number of random moves
                        if(deposit-1) then collect deposit-1  else {}
                        while (budget) { # still our region
                          opponentLoc = opponent
                          if (opponentLoc / 10 - 1)
                          then  # opponent afar
                            if (opponentLoc % 10 - 5) then move upleft
                            else if (opponentLoc % 10 - 4) then move downleft
                            else if (opponentLoc % 10 - 3) then move down
                            else if (opponentLoc % 10 - 2) then move downright
                            else if (opponentLoc % 10 - 1) then move upright
                            else move up
                          else if (opponentLoc)
                          then  # opponent adjacent to city crew
                            if (opponentLoc % 10 - 5) then {
                              cost = 10 ^ (nearby upleft % 100 + 1)
                              if (budget - cost) then shoot upleft cost else {shoot upleft 10^(nearby upleft%100)}
                            }
                            else if (opponentLoc % 10 - 4) then {
                              cost = 10 ^ (nearby downleft % 100 + 1)
                              if (budget - cost) then shoot downleft cost else {shoot downleft 10^(nearby downleft%100)}
                            }
                            else if (opponentLoc % 10 - 3) then {
                              cost = 10 ^ (nearby down % 100 + 1)
                              if (budget - cost) then shoot down cost else {shoot down 10^(nearby down%100)}
                            }
                            else if (opponentLoc % 10 - 2) then {
                              cost = 10 ^ (nearby downright % 100 + 1)
                              if (budget - cost) then shoot downright cost else {shoot downright 10^(nearby downright%100)}
                            }
                            else if (opponentLoc % 10 - 1) then {
                              cost = 10 ^ (nearby upright % 100 + 1)
                              if (budget - cost) then shoot upright cost else {shoot upright 10^(nearby upright%100)}
                            }
                            else {
                              cost = 10 ^ (nearby up % 100 + 1)
                              if (budget - cost) then shoot up cost else {shoot up 10^(nearby up%100)}
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
                        #if (budget - 1) then invest 1 else {}
                        \s""";

        String s2 = "done";
//"""
//                while(budget) {
//                    if(maxdeposit-deposit-interest)
//                    then if (budget-maxdeposit+deposit+interest)
//                         then invest maxdeposit-deposit-interest
//                         else invest budget
//                    else collect interest
//                    dir = random
//                    if(dir%6-4)
//                    then move up
//                    else if(dir%6-3)
//                         then move upright
//                         else if(dir%6-2)
//                              then move downright
//                              else if(dir%6-1)
//                                   then move down
//                                   else if(dir%6)
//                                        then move downleft
//                                        else move upleft
//                }
//                """;

        Plan p1 = new PlanParser(new PlanTokenizer(s1)).parse();
        Plan p2 = new PlanParser(new PlanTokenizer(s2)).parse();

        for (int i=0;i<50;i++) {
            g.addPlayer("p"+i);
            g.devisePlan(i, p1);
        }

        System.out.println(g.getPlayer().getName()+" turn");
        while (g.executePlan()) {
            g.nextTurn();
            System.out.println(g.getPlayer().getName()+" turn");
        }

        System.out.println(g.getPlayer().getName()+" win!!!");
//        assertEquals("first", g.getPlayer().getName());
    }

    @Test
    public void loop2() throws SyntaxError, EvalError {
        for (int i=0;i<1;i++) {
            System.out.println("\n\n round "+i);
            gameTest2();
        }
    }
}