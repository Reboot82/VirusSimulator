
import java.awt.*;

public class Recovered extends Critter {
    private Color color = Color.WHITE;
    private final String image = "H";
    private int moveCount = 0;

    public Recovered() {
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
        else if (info.leftThreat() || info.rightThreat()) {
            action = Action.HOP;
        }
        else if (info.getLeft() == Neighbor.OTHER || moveCount%3 == 0) {
            action = Action.LEFT;
        }
        else if (info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.OTHER) {
            action = Action.RIGHT;
        }
        else if (info.getFront() == Neighbor.OTHER) {
            action = Action.INFECT;
        }
        else {
            action = Action.HOP;
        }
        moveCount++;
        return action;
    }
}