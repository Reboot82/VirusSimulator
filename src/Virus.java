
import java.awt.*;

public class Virus extends Critter {
    private Color[] color = {Color.CYAN, Color.BLUE, Color.GREEN, Color.RED, Color.BLACK};
    private final String image = "V";
    private int moveCount = 0;
    private int infectiousness = 0;

    public Virus() {
        super();
    }

    public void setInfectiousness() {
        infectiousness = CritterMain.getInfector()-1;
    }

    @Override
    public Color getColor() {
        setInfectiousness();
        return this.color[infectiousness];
    }

    @Override
    public String toString() {
        return image;
    }

    @Override
    public Action getMove(CritterInfo info) {
        this.setMoveCount(moveCount);
        Action action = null;
        if (info.frontThreat()) {
            action = infect();
        }
        else if (info.getFront() == Neighbor.WALL || info.getFront() == Neighbor.SAME) {
            action = Action.LEFT;
        }
        else if (info.getFront() == Neighbor.OTHER) {
            action = infect();
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

    public Action infect() {
        Action action = null;
        switch (infectiousness) {
            case 0:
                if (moveCount%5 == 0) {
                    action = Action.INFECT;
                }
                else {
                    action = pickMove();
                }
                break;
            case 1:
                if (moveCount%4 == 0) {
                    action = Action.INFECT;
                }
                else {
                    action = pickMove();
                }
                break;
            case 2:
                if (moveCount%3 == 0) {
                    action = Action.INFECT;
                }
                else {
                    action = pickMove();
                }
                break;
            case 3:
                if (moveCount%2 == 0) {
                    action = Action.INFECT;
                }
                else {
                    action = pickMove();
                }
                break;
            case 4:
                action = Action.INFECT;
                break;
            default:
                action = pickMove();
        }
        return action;
    }

    public Action superInfect(CritterInfo info) {
        Action action = null;
        if (info.frontThreat()) {
            action = infect();
        }
        else if (info.getFront() == Neighbor.OTHER) {
            action = infect();
        }
        else if (info.getRight() == Neighbor.OTHER) {
            action = Action.RIGHT;
        }
        else if (info.getLeft() == Neighbor.OTHER) {
            action = Action.LEFT;
        }
        else {
            action = pickMove();
        }
        moveCount++;
        return action;
    }

    public Action fourInfect(CritterInfo info) {
        Action action = null;
        if (info.frontThreat()) {
            action = Action.INFECT;
        }
        else if (info.getFront() == Neighbor.OTHER) {
            action = Action.INFECT;
        }
        else if (info.getRight() == Neighbor.OTHER) {
            action = Action.RIGHT;
        }
        else if (info.getLeft() == Neighbor.OTHER) {
            action = Action.HOP;
        }
        else {
            action = pickMove();
        }
        moveCount++;
        return action;
    }
}