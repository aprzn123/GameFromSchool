package world;

import java.util.ArrayList;

import entities.Entity;
import entities.Player;
import io.ColoredText;
import io.ColoredTextBuilder;

public class Room {
    private Tile[][] tiles;
    private ColoredText symbol;
    private ArrayList<Entity> entities;
    
    public Room(Tile[][] tiles, ColoredText symbol) {
        this.tiles = tiles;
        this.symbol = symbol;
        this.entities = new ArrayList<Entity>();
    }

    public int getWidth() {
        return tiles[0].length;
    }
    
    public int getHeight() {
        return tiles.length;
    }

    public ColoredText getSymbol() {
        return symbol;
    }

    public ArrayList<Entity> entitiesOnTile(int x, int y) {
        ArrayList<Entity> out = new ArrayList<Entity>();
        for (Entity entity : entities) {
            if (entity.isAtPosition(x, y)) {
                out.add(entity);
            }
        }
        return out;
    }

    public ArrayList<Entity> visibleEntitiesOnTile(int x, int y) {
        ArrayList<Entity> out = new ArrayList<Entity>();
        for (Entity entity : entities) {
            if (entity.isAtPosition(x, y) && !entity.isVisible()) {
                out.add(entity);
            }
        }
        return out;
    }

    public Tile getTile(int x, int y) {
        return tiles[y][x];
    }

    public String toString() {
        ColoredTextBuilder str = ColoredText.builder();
        for (int rowIdx = 0; rowIdx < tiles.length; rowIdx++) {
            for (int colIdx = 0; colIdx < tiles[rowIdx].length; colIdx++) {
                ArrayList<Entity> es = visibleEntitiesOnTile(colIdx, rowIdx);
                if (es.size() == 0) {
                    str.then(Tile.symbol(getTile(colIdx, rowIdx))).then(" ");
                } else {
                    str.then(es.get(0).getSymbol()).then(" ");
                }
            }
            str.then("\n");
        }
        return str.build().toString();
    }
    
    public boolean canEnterTile(int x, int y, boolean includeCollision) {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            return false;
        }
        if (includeCollision) {
            if (getTile(x, y) == Tile.DOOR_CLOSED || getTile(x, y) == Tile.WALL) {
                return false;
            }
            for (Entity ent : entitiesOnTile(x, y)) {
                if (ent.hasCollision()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean hasPlayer() {
        for (Entity ent : entities) {
            if (ent instanceof Player) return true;
        }
        return false;
    }

    public void addEntity(Entity ent) {
        entities.add(ent);
        ent.setRoom(this);
    }

    public void refreshDeletions() {
        for (int i = entities.size() - 1; i >= 0; i--) {
            if (entities.get(i).readyForDeletion()) {
                entities.remove(i);
            }
        }
    }

    public boolean canOpenDoor(int x, int y) {
        return getTile(x, y) == Tile.DOOR_CLOSED;
    }
    
    public void openDoor(int x, int y) {
        if (canOpenDoor(x, y)) tiles[y][x] = Tile.DOOR_OPEN;
    }
}
