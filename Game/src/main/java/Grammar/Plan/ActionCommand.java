package Grammar.Plan;

import Grammar.Plan.Plan;

public class ActionCommand extends Plan {
    private String command;

    public ActionCommand(String command){
        this.command = command;
    }

    public void eval(Player p, Territory t) {

    }
}

