
import java.awt.*;

public class Giant extends Critter {
    private Color color = Color.GRAY;
    private final String[] images = {"fee", "fie", "fo", "fum"};
    private int moveCount = 0;
    private int counter = 0;


    public Giant() {
        super();
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        if (moveCount == 0) {
            return images[0];
        }
        else if (moveCount%6 == 0) {
            counter++;
        }
        return images[counter%4];
    }

    @Override
    public Action getMove(CritterInfo info) {
        Action action = null;
        if (info.frontThreat()) {
            action = Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            action = Action.HOP;
        } else {
            action = Action.RIGHT;
        }
        moveCount++;
        return action;
    }
}
