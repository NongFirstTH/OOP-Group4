package GamePlay;
import Grammar.Expression.EvalError;
import Grammar.Parse.PlanParser;
import Grammar.Parse.PlanTokenizer;
import Grammar.Parse.SyntaxError;
import Grammar.Plan.Plan;
import Chat.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class GameController {
    Game g;
    private final SimpMessageSendingOperations messageSendingOperations;

//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public ChatMessage newGame(ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) throws SyntaxError, EvalError {
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//        g = new Game(chatMessage.getSender());
//        return chatMessage;
//    }
    @MessageMapping("/game.new")
    @SendTo("/topic/public")
    public ChatMessage newGame(InitGame init, SimpMessageHeaderAccessor headerAccessor) throws SyntaxError, EvalError {
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getText());
        g = new Game(init);
        return ChatMessage.x("create Game success");
    }

//    @MessageMapping("/game.new")
//    @SendTo("/topic/public")
//    public ChatMessage newGame(Wrapper config, SimpMessageHeaderAccessor headerAccessor) throws SyntaxError, EvalError {
//        headerAccessor.getSessionAttributes().put("username", config.getS());
////        g = new Game(config.getS());
//        return ChatMessage.x("success");
//    }

    @MessageMapping("/game.addPlayer")
    @SendTo("/topic/public")
    public ChatMessage addPlayer(Wrapper name) {
        g.addPlayer(name.getText());
        return ChatMessage.x("add "+ name.getText());
    }

    @MessageMapping("/game.devise")
    @SendTo("/topic/public")
    public ChatMessage devise(Wrapper plan) throws SyntaxError {
        g.devisePlan(plan.getN(), parsePlan(plan.getText()));
//        messageSendingOperations.convertAndSend("/topic/public", ChatMessage.x("--add--"));
        regionMutate(1,2,3,4);
        return ChatMessage.x("devise success?");
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

    public void regionMutate(int row, int col, int player, long deposit) {
        messageSendingOperations.convertAndSend("/topic/region", new RegionWrap(row, col, player, deposit));
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

