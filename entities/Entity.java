package entities;

import io.ColoredText;
import world.Direction;
import world.Room;

public class Entity {
    private ColoredText symbol;
    private int x;
    private int y;
    private boolean canCollide;
    protected Room room;
    private boolean forDeletion;
    protected boolean visible;
    
    public Entity(ColoredText symbol, boolean canCollide, Room room) {
        this(symbol, 0, 0, canCollide, room);
    }

    public Entity(ColoredText symbol, int x, int y, Room room) {
        this(symbol, x, y, false, room);
    }
    
    public Entity(ColoredText symbol, int x, int y, boolean canCollide, Room room) {
        forDeletion = false;
        visible = false;
        this.symbol = symbol;
        this.x = x;
        this.y = y;
        this.canCollide = canCollide;
        room.addEntity(this);
    }

    public boolean isVisible() {
        return visible;
    }

    public int getX() {
        return x;
    }

    public void moveRooms(Room newRoom, int newX, int newY) {
        delete();
        newRoom.addEntity(this);
        setPosition(newX, newY);
    }

    // ONLY FOR USE BY ROOM
    // TO SET AN ENTITY'S ROOM, USE ROOM.ADDENTITY
    // IF THE ENTITY ALREADY HAS A ROOM, DELETE IT FIRST
    public void setRoom(Room room) {
        this.room = room;
    }

    public int getY() {
        return y;
    }

    public boolean hasCollision() {
        return canCollide;
    }

    public boolean isAtPosition(int x, int y) {
        return this.x == x && this.y == y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ColoredText getSymbol() {
        return symbol;
    }

    public void move(Direction dir) {
        int newX = Direction.newX(x, dir);
        int newY = Direction.newY(y, dir);
        if (room.canEnterTile(newX, newY, canCollide)) {
            x = newX;
            y = newY;
        }
    }

    public boolean readyForDeletion() {
        return forDeletion;
    }

    public void delete() {
        forDeletion = true;
        room.refreshDeletions();
        forDeletion = false;
    }

}
