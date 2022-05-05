package entities;

import io.ColoredText;
import world.Room;

public class Victory extends Entity {
    public Victory(int x, int y, Room room) {
        super(new ColoredText("V", ColoredText.GREEN), x, y, false, room);
    }
}
