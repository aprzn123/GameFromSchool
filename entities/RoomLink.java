package entities;

import io.ColoredText;
import world.Room;

public class RoomLink extends Entity {
    private Room linkedRoom;
    private int linkedX;
    private int linkedY;

    public static void linkRooms(Room room1, int x1, int y1, Room room2, int x2, int y2) {
        new RoomLink(room1, x1, y1, room2, x2, y2);
        new RoomLink(room2, x2, y2, room1, x1, y1);
    }

    public RoomLink(Room room, int x, int y, Room linkedRoom, int linkX, int linkY) {
        super(new ColoredText("a"), x, y, room);
        this.linkedRoom = linkedRoom;
        this.linkedX = linkX;
        this.linkedY = linkY;
        visible = true;
    }

    public void transferPlayer(Player player) {
        player.moveRooms(linkedRoom, linkedX, linkedY);
    }
}
