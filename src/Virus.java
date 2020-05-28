
import java.awt.*;

public class Virus extends Critter {
    private Color[] color = {Color.MAGENTA, Color.BLUE, Color.GRAY, Color.DARK_GRAY, Color.BLACK};
    private int unicode = 0x1F647;
    private final String image = getEmojiByUnicode(unicode);
    private int moveCount = 0;
    private int infectiousness = 0;

    public int getInfectiousness() {
        return infectiousness;
    }

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
            action = Action.HOP;
        }
        moveCount++;
        return action;
    }

    public Action pickMove() {
        int rnd = (int)(Math.random()*(10));
        return switch (rnd) {
            case 1,5,9 -> Action.HOP;
            case 2 -> Action.LEFT;
            case 3,4,6,7,8 -> Action.RIGHT;
            default -> null;
        };
    }

    public Action infect() {
        Action action = null;
        switch (infectiousness) {
            case 0:
                if (moveCount%14 == 0) {
                    action = Action.INFECT;
                }
                else {
                    action = pickMove();
                }
                break;
            case 1:
                if (moveCount%9 == 0) {
                    action = Action.INFECT;
                }
                else {
                    action = pickMove();
                }
                break;
            case 2:
                if (moveCount%5 == 0) {
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

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}