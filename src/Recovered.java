
import java.awt.*;

public class Recovered extends Critter {
    private Color color = Color.WHITE;
    private int unicode = 0x1F64F;
    private final String image = getEmojiByUnicode(unicode);
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
            case 1,4,5,8,9 -> Action.HOP;
            case 2,6 -> Action.LEFT;
            case 3,7 -> Action.RIGHT;
            default -> null;
        };
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}