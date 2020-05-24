import java.awt.*;

public class Deceased extends Critter{
    private final Color color = Color.RED;
    private String image = "X";
    private int moveCount = 0;

    public Deceased() {
        super();
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    public String toString() {
        return this.image;
    }

    @Override
    public Action getMove(CritterInfo info) {
        Action action = Action.LEFT;
        moveCount++;
        return action;
    }
}
