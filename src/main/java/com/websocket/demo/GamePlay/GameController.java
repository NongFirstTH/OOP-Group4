package com.websocket.demo.GamePlay;
import com.websocket.demo.GamePlay.Wrapper.*;
import com.websocket.demo.Grammar.Expression.EvalError;
import com.websocket.demo.Grammar.Parse.PlanParser;
import com.websocket.demo.Grammar.Parse.PlanTokenizer;
import com.websocket.demo.Grammar.Parse.SyntaxError;
import com.websocket.demo.Grammar.Plan.Plan;
import com.websocket.demo.Chat.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Controller
public class GameController {
    private Game g;
    private final SimpMessageSendingOperations messageSendingOperations;
    private int ready=0;
    private String state = "\"START\"";

    @MessageMapping("/game.start")
    public void start() throws SyntaxError, EvalError {
        if(g!=null) {
            messageSendingOperations.convertAndSend("/topic/init", "");
            messageSendingOperations.convertAndSend("/topic/setState", state);
        }
    }

    @MessageMapping("/game.new")
    public void newGame(InitGame init) throws SyntaxError, EvalError {
        g = new Game(init);
        state = "\"ADD\"";
        messageSendingOperations.convertAndSend("/topic/init", "");
//        messageSendingOperations.convertAndSend("/topic/setState", state);
    }

    @MessageMapping("/game.addPlayer")
    public void addPlayer(StringWrap name) {
        g.addPlayer(name.getText());
        messageSendingOperations.convertAndSend("/topic/addPlayer", g.getPlayers());
    }

    @MessageMapping("/game.getPlayers")
    @SendTo("/topic/addPlayer")
    public Set<String> getPlayers() {
        return g==null?new HashSet<>():g.getPlayers();
    }

    @MessageMapping("/game.devise")
    public void devise(PlanWrap plan) {
        try {
            g.devisePlan(plan.getPlayer(), parsePlan(plan.getPlan()));
            if (++ready >= g.getPlayers().size()) {
                messageSendingOperations.convertAndSend("/topic/turn", "\"" + g.getPlayer().getName() + "\"");
                state = "\"TURN\"";
                messageSendingOperations.convertAndSend("/topic/setState", "\"TURN\"");
            }
            messageSendingOperations.convertAndSend("/topic/plan." + plan.getPlayer(), "true");
        } catch (SyntaxError | NoSuchElementException e) {
            messageSendingOperations.convertAndSend("/topic/plan." + plan.getPlayer(), "false");
        }
    }

    @MessageMapping("/game.setState")
    @SendTo("/topic/setState")
    public String setState(String plan) {
        state = "\""+plan+"\"";
        if (plan.equals("INIT")) {
            g = null;
            ready = 0;
        }
        if(plan.equals("DEVISE")||plan.equals("REVISE")||plan.equals("TURN")) getTerritory();
        return state;
    }

    @MessageMapping("/game.revise")
    @SendTo("/topic/territory")
    public TerritoryWrap revise(StringWrap plan) {
        try {
            g.revisePlan(parsePlan(plan.getText()));
            if (!g.executePlan()) {
                state = "\"END\"";
                messageSendingOperations.convertAndSend("/topic/setState", "\"END\"");
            }
            nextTurn();
            state = "\"TURN\"";
            messageSendingOperations.convertAndSend("/topic/setState", "\"TURN\"");
            messageSendingOperations.convertAndSend("/topic/plan." + g.getPlayer().getName(), "true");
        } catch (SyntaxError | NoSuchElementException | EvalError e) {
            messageSendingOperations.convertAndSend("/topic/plan." + g.getPlayer().getName(), "false");
        }
        return g.getMap();
    }

    @MessageMapping("/game.execute")
    @SendTo("/topic/territory")
    public TerritoryWrap execute() throws EvalError {
        if (!g.executePlan()) {
            state = "\"END\"";
            messageSendingOperations.convertAndSend("/topic/setState", "\"END\"");
        }
        nextTurn();
        return g.getMap();
    }

    public void nextTurn() {
        g.nextTurn();
        messageSendingOperations.convertAndSend("/topic/turn", "\""+g.getPlayer().getName()+"\"");
    }

    @MessageMapping("/game.getTerritory")
//    @SendTo("/topic/territory")
    public void getTerritory() {
        messageSendingOperations.convertAndSend("/topic/territory", g.getMap());
    }

    public void regionMutate(int row, int col, int player, long deposit) {
//        messageSendingOperations.convertAndSend("/topic/region", new RegionWrap(row, col, player, deposit));
    }

    @SendTo("/topic/public")
    public ChatMessage sendMessage(String s) {
        return ChatMessage.x(s);
    }
//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public ChatMessage addUser(ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//        return chatMessage;
//    }

    private Plan parsePlan(String plan) throws SyntaxError {
        return new PlanParser(new PlanTokenizer(plan)).parse();
    }
}

