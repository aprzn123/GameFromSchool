package world;

import io.ColoredText;
import io.ColoredTextBuilder;

public class WorldMap {
    private Room[][] rooms;
    private ColoredText title;
    
    public WorldMap(Room[][] rooms, ColoredText title) {
        this.rooms = rooms;
        this.title = title;
    }

    public int getWidth() {
        return rooms[0].length;
    }
    
    public int getHeight() {
        return rooms.length;
    }

    public Room getRoom(int x, int y) {
        return rooms[y][x];
    }
    
    public String toString() {
        ColoredTextBuilder str = ColoredText.builder();
        str.then(title).then("\n");

        for (int rowIdx = 0; rowIdx < getHeight(); rowIdx++) {
            for (int colIdx = 0; colIdx < getWidth(); colIdx++) {
                if (getRoom(colIdx, rowIdx).hasPlayer()) str.then("Y").then(" ");
                else str.then(getRoom(colIdx, rowIdx).getSymbol()).then(" ");
            }
            str.then("\n");
        }
        return str.build().getUncoloredString();
    }
}
