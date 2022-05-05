package entities;

import io.ColoredText;
import world.Room;

public class Key extends Entity {
    public Key(int x, int y, Room room) {
        super(new ColoredText("K", ColoredText.YELLOW), x, y, false, room);
    }
}
