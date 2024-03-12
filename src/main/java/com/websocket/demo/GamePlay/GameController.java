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

@RequiredArgsConstructor
@Controller
public class GameController {
    Game g;
    private final SimpMessageSendingOperations messageSendingOperations;

    @MessageMapping("/game.new")
    public void newGame(InitGame init) throws SyntaxError, EvalError {
        g = new Game(init);
        messageSendingOperations.convertAndSend("/topic/status", "create Game success");
    }

    @MessageMapping("/game.addPlayer")
    public void addPlayer(StringWrap name) {
        g.addPlayer(name.getText());
        messageSendingOperations.convertAndSend("/topic/status", "add "+name.getText());
    }

    @MessageMapping("/game.devise")
    public void devise(PlanWrap plan) throws SyntaxError {
        g.devisePlan(plan.getPlayer(), parsePlan(plan.getPlan()));
        messageSendingOperations.convertAndSend("/topic/status", "devise success???");
    }

    @MessageMapping("/game.revise")
    @SendTo("/topic/public")
    public ChatMessage revise(Wrapper plan) throws SyntaxError {
        g.revisePlan(parsePlan(plan.getText()));
        return ChatMessage.x("revise success?");
    }

    @MessageMapping("/game.execute")
    @SendTo("/topic/public")
    public ChatMessage execute() throws EvalError {
        g.executePlan();
        return ChatMessage.x("execute success?");
    }

    @MessageMapping("/game.nextTurn")
    @SendTo("/topic/public")
    public ChatMessage nextTurn() {
        g.nextTurn();
        return ChatMessage.x("next turn success?");
    }

    @MessageMapping("/game.getTerritory")
    @SendTo("/topic/territory")
    public RegionWrap[][] getTerritory() {
        return g.getTerritory().wrap();
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

