
import java.awt.*;

public class Virus extends Critter {
    private Color color = Color.BLACK;
    private final String image = "V";
    private int moveCount = 0;

    public Virus() {
        super();
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return image;
    }

    @Override
    public Action getMove(CritterInfo info) {
        Action action = null;
        if (info.frontThreat()) {
            action = Action.INFECT;
        }
        else if (info.getFront() == Neighbor.WALL || info.getFront() == Neighbor.SAME) {
            action = Action.LEFT;
        }
        else if (info.getFront() == Neighbor.OTHER) {
            action = Action.INFECT;
        }
        else {
            action = pickMove();
        }
        moveCount++;
        return action;
    }

    public Action pickMove() {
        int rnd = (int)(Math.random()*(4));
        return switch (rnd) {
            case 1 -> Action.HOP;
            case 2 -> Action.LEFT;
            case 3 -> Action.RIGHT;
            default -> null;
        };
    }
}