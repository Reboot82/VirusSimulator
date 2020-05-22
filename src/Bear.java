
import java.awt.*;

public class Bear extends Critter {
    private Color color = Color.BLACK;
    private final String[] images = {"/", "\\"};
    private int moveCount = 0;

    public Bear(boolean polar) {
        super();
        if (polar) color = Color.WHITE;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return images[moveCount%2];
    }

    @Override
    public Action getMove(CritterInfo info) {
        Action action = null;
        if (info.frontThreat()) {
            action = Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            action = Action.HOP;
        } else {
            action = Action.LEFT;
        }
        moveCount++;
        return action;
    }
}
