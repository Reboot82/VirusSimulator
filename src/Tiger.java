
import java.awt.*;
import java.util.Random;

public class Tiger extends Critter {
    private Color color;
    private String image = "TGR";
    private int moveCount = 0;

    public Tiger() {
        super();
    }

    @Override
    public Color getColor() {
        if (moveCount%3 == 0) {
            int rnd = new Random().nextInt(3);
            switch (rnd) {
                case 0 -> this.color = Color.RED;
                case 1 -> this.color = Color.GREEN;
                case 2 -> this.color = Color.BLUE;
                default -> {
                    System.out.println("This shouldn't happen...");
                    return this.color;
                }
            }
        }
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
            action = Action.INFECT;
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