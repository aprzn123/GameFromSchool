package entities;

import io.ColoredText;
import io.IOUtils;
import world.Direction;
import world.Room;

public class Player extends Entity {
    private ColoredText name;
    int heldKeys;
    long time;
    public Player(int x, int y, Room room, String name) {
        super(new ColoredText("P", ColoredText.CYAN), x, y, true, room);
        this.name = new ColoredText(name, ColoredText.CYAN);
        heldKeys = 0;
        time = System.currentTimeMillis();
    }

    public void drawRoom() {
        System.out.println(room);
    }

	public ColoredText getName() {
		return name;
    }
    
    @Override
    public void move(Direction dir) {
        int prevX = getX();
        int prevY = getY();
        super.move(dir);
        for (Entity ent : room.entitiesOnTile(getX(), getY())) {
            if (ent instanceof RoomLink && (prevX != getX() || prevY != getY())) {
                ((RoomLink) ent).transferPlayer(this);
            }
            if (ent instanceof Victory) {
                win();
            }
        }
    }

    private void win() {
        IOUtils.gradualPrintln(50, ColoredText.builder().green("You win, ").then(name).green("!").build().toString());
        double playTime = (System.currentTimeMillis() - time) / 1000.0;
        IOUtils.gradualPrintln(50, ColoredText.builder().green("You beat the game in ").purple(Double.toString(playTime)).green(" seconds").build().toString());
        System.exit(0);
    }

	public void onUse() {
        for (int i = 0; i < room.entitiesOnTile(getX(), getY()).size(); i++) {
            if (room.entitiesOnTile(getX(), getY()).get(i) instanceof Key) {
                room.entitiesOnTile(getX(), getY()).get(i).delete();
                heldKeys++;
                return;
            }
        }
        if (room.canOpenDoor(getX(), getY() + 1) && heldKeys > 0) {heldKeys--; room.openDoor(getX(), getY() + 1);}
        if (room.canOpenDoor(getX(), getY() - 1) && heldKeys > 0) {heldKeys--; room.openDoor(getX(), getY() - 1);}
        if (room.canOpenDoor(getX() + 1, getY()) && heldKeys > 0) {heldKeys--; room.openDoor(getX() + 1, getY());}
        if (room.canOpenDoor(getX() - 1, getY()) && heldKeys > 0) {heldKeys--; room.openDoor(getX() - 1, getY());}
    }
    
    public int asdf() {
        return heldKeys;
    }
}
