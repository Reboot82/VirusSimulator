import java.awt.*;

public class WhiteTiger extends Tiger{
    private final Color color = Color.WHITE;
    private String image = "tgr";
    private int moveCount = 0;

    public WhiteTiger() {
        super();
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    public void infectCritter() {
        this.image = "TGR";
    }

    public String toString() {
        return this.image;
    }

    @Override
    public Action getMove(CritterInfo info) {
        Action action = null;
        if (info.frontThreat()) {
            action = Action.INFECT;
            infectCritter();
        } else if (info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.WALL) {
            action = Action.LEFT;
        } else if (info.getFront() == Neighbor.SAME) {
            action = Action.RIGHT;
        } else {
            action = Action.HOP;
        }
        moveCount++;
        return action;
    }
}
