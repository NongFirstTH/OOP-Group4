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
import java.util.Set;

@RequiredArgsConstructor
@Controller
public class GameController {
    Game g;
    private final SimpMessageSendingOperations messageSendingOperations;
    private int ready=0;


    @MessageMapping("/game.start")
    public void start() throws SyntaxError, EvalError {
        if(g!=null) {
            messageSendingOperations.convertAndSend("/topic/init", "");
        }
    }

    @MessageMapping("/game.new")
    public void newGame(InitGame init) throws SyntaxError, EvalError {
        g = new Game(init);
        messageSendingOperations.convertAndSend("/topic/init", "");
    }

    @MessageMapping("/game.addPlayer")
    public void addPlayer(StringWrap name) {
        g.addPlayer(name.getText());
        messageSendingOperations.convertAndSend("/topic/addPlayer", g.getPlayers());
        getTerritory();
    }

    @MessageMapping("/game.getPlayers")
    @SendTo("/topic/addPlayer")
    public Set<String> getPlayers() {
        return g==null?new HashSet<>():g.getPlayers();
    }

    @MessageMapping("/game.devise")
    public void devise(PlanWrap plan) throws SyntaxError {
        g.devisePlan(plan.getPlayer(), parsePlan(plan.getPlan()));
        if(++ready>=g.getPlayers().size()) {
            messageSendingOperations.convertAndSend("/topic/turn", "\""+g.getPlayer().getName()+"\"");
            messageSendingOperations.convertAndSend("/topic/setState", "\"TURN\"");
        }
    }

    @MessageMapping("/game.revise")
    @SendTo("/topic/territory")
    public TerritoryWrap revise(StringWrap plan) throws SyntaxError, EvalError {
        g.revisePlan(parsePlan(plan.getText()));
        g.executePlan();
        nextTurn();
        messageSendingOperations.convertAndSend("/topic/setState", "\"TURN\"");
        return g.getMap();
    }

    @MessageMapping("/game.execute")
    @SendTo("/topic/territory")
    public TerritoryWrap execute() throws EvalError {
        g.executePlan();
        nextTurn();
        return g.getMap();
    }

    public void nextTurn() {
        g.nextTurn();
        messageSendingOperations.convertAndSend("/topic/turn", "\""+g.getPlayer().getName()+"\"");
    }

//    @MessageMapping("/game.getTerritory")
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

