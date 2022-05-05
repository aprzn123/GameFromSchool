package world;

import io.ColoredText;

public enum Tile {
    FLOOR,
    WALL,
    DOOR_CLOSED,
    DOOR_OPEN;

    public static ColoredText symbol(Tile tile) {
        if (tile == FLOOR) {
            return new ColoredText(".");
        } else if (tile == WALL) {
            return new ColoredText("Z", ColoredText.PURPLE);
        } else if (tile == DOOR_CLOSED) {
            return new ColoredText("0", ColoredText.RED);
        } else if (tile == DOOR_OPEN) {
            return new ColoredText("O", ColoredText.GREEN);
        } else {
            return new ColoredText("!", ColoredText.RED);
        }
    }
}
