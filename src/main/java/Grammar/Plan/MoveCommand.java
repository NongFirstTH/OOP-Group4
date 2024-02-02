package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.HashMap;

public record MoveCommand (Direction dir) implements Plan {
    @Override
    public boolean eval(HashMap<String, Integer> bindings, Player p, Territory t) {
        return true;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append("move ").append(dir).append("\n");
    }

    public boolean move(Player player, Territory territory) {
        // Update player's position or take other actions
        if (dir.equals("left")) {
            // Move left
            System.out.println("Moving left.");
            // TODO: Update player's position in the territory
            return true; // Return true if the move is successful
        } else if (dir.equals("right")) {
            // Move right
            System.out.println("Moving right.");
            // TODO: Update player's position in the territory
            return true;
        } else if (dir.equals("up")) {
            // Move up
            System.out.println("Moving up.");
            // TODO: Update player's position in the territory
            return true;
        } else if (dir.equals("down")) {
            // Move down
            System.out.println("Moving down.");
            // TODO: Update player's position in the territory
            return true;
        } else {
            System.out.println("Invalid direction.");
            return false; // Return false for an invalid direction
        }
    }
}
