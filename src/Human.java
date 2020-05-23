
import java.awt.*;

public class Human extends Critter {
    private Color color = Color.YELLOW;
    private final String image = "H";
    private int moveCount = 0;
    private int counter = 0;


    public Human() {
        super();
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return this.image;
    }

    @Override
    public Action getMove(CritterInfo info) {
        Action action = null;
        if (info.frontThreat()) {
            action = Action.LEFT;
        }
        else if (info.getFront() != Neighbor.EMPTY){
            action = Action.RIGHT;
        }
        else {
            action = pickMove();
        }
        moveCount++;
        return action;
    }

    public Action pickMove() {
        int rnd = (int)(Math.random()*(10));
        return switch (rnd) {
            case 1,4,5,6,7,8,9 -> Action.HOP;
            case 2 -> Action.LEFT;
            case 3 -> Action.RIGHT;
            default -> null;
        };
    }
}
