package Grammar.Plan;

import Grammar.Plan.Plan;

public class MoveCommand extends Plan {
    private String dir;

    public MoveCommand(String dir){
        this.dir = dir;
    }
    public void eval(Player p, Territory t){

    }
}
