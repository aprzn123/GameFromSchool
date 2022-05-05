import entities.Key;
import entities.Player;
import entities.RoomLink;
import entities.Victory;
import io.ColoredText;
import io.IOUtils;
import io.InputMap;
import world.Direction;
import world.Room;
import world.Tile;
import world.WorldMap;
import java.lang.Math;

public class Main {

    public static void main(String[] args) {
        IOUtils.gradualPrintln(30, ColoredText.builder()
                        .yellow("Instructions:\n")
                        .cyan("Use W, A, S, and D to move around the map.\n")
                        .yellow("A ").purple("Z").yellow(" is a wall.\n")
                        .cyan("The ").then(".", ColoredText.RESET).cyan(" are the floor.\n")
                        .yellow("The ").cyan("P").yellow(" is you, and your goal is to get to the ").green("V").yellow(".\n")
                        .cyan("The ").yellow("K").cyan(" is a key, which is used to open doors.\n")
                        .yellow("Doors look like ").red("0").yellow(" when they're closed, and ").green("O").yellow(" when they're open.\n")
                        .cyan("Press E while on a key to pick it up, and press E while in front of a door to open it with your key.\n")
                        .yellow("Press M to view a map of the world.\n")
                        .cyan("Press Q at any time to quit.\n\n")
                        .yellow("The story: You are a robber trying to steal from a vault, and you need to figure out how to get into it.\n")
                        .cyan("Once you beat the game, try for a better time!\n")
                        .then("\n\n", ColoredText.RESET)
                        .build().toString());
        Tile w = Tile.WALL;
        Tile f = Tile.FLOOR;
        Tile c = Tile.DOOR_CLOSED;
        Tile[][] dummy = {{f}};
        Tile[][] uOpen = {
            {w, w, w, f, w, w, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, w, w,w, w, w,  w}
        };
        Tile[][] lOpen = {
            {w, w, w, w, w, w, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {f, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, w, w,w, w, w,  w}
        };
        Tile[][] lrDoor = {
            {w, w, w, w, w, w, w},
            {w, f, f, w, f, f, w},
            {w, f, f, w, f, f, w},
            {f, f, f, c, f, f, f},
            {w, f, f, w, f, f, w},
            {w, f, f, w, f, f, w},
            {w, w, w,w, w, w,  w}
        };
        Tile[][] udDoor = {
            {w, w, w, f, w, w, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, w, w, c, w, w, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, w, w, f, w, w, w}
        };
        Tile[][] lrdOpen = {
            {w, w, w, w, w, w, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {f, f, f, f, f, f, f},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, w, w,f, w, w,  w}
        };
        Tile[][] rdOpen = {
            {w, w, w, w, w, w, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, f},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, w, w,f, w, w,  w}
        };
        Tile[][] ldOpen = {
            {w, w, w, w, w, w, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {f, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, w, w,f, w, w,  w}
        };
        Tile[][] udOpen = {
            {w, w, w, f, w, w, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, w, w,f, w, w,  w}
        };
        Tile[][] urOpen = {
            {w, w, w, f, w, w, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, f},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, w, w,w, w, w,  w}
        };
        Tile[][] lrOpen = {
            {w, w, w, w, w, w, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {f, f, f, f, f, f, f},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, w, w,w, w, w,  w}
        };
        Tile[][] ludOpen = {
            {w, w, w, f, w, w, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {f, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, w, w, f, w, w, w}
        };
        Tile[][] ulOpen = {
            {w, w, w, f, w, w, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {f, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, w, w, w, w, w, w}
        };
        Tile[][] lurOpen = {
            {w, w, w, f, w, w, w},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {f, f, f, f, f, f, f},
            {w, f, f, f, f, f, w},
            {w, f, f, f, f, f, w},
            {w, w, w, w, w, w, w}
        };
        Tile[][] rdMaze2x = {
            {w, w, w, w, w, w, w, w, w, w, w, w, w, w},
            {w, f, f, f, f, f, f, f, f, f, f, f, f, w},
            {w, f, w, w, w, w, w, f, w, w, w, w, w, w},
            {w, f, w, w, f, w, f, f, w, f, f, f, f, f},
            {w, f, w, w, f, w, f, w, w, w, f, w, w, w},
            {w, f, w, w, f, f, f, f, f, f, f, w, f, w},
            {w, f, w, w, f, w, w, w, w, w, f, w, f, w},
            {w, f, w, w, f, f, f, f, f, w, f, w, f, w},
            {w, f, w, w, w, w, w, w, f, w, f, f, f, w},
            {w, f, w, w, f, f, w, f, f, w, w, w, f, w},
            {w, f, w, f, f, w, w, w, w, w, f, w, f, w},
            {w, f, w, w, f, w, f, f, f, f, f, w, f, w},
            {w, f, f, f, f, f, f, w, w, w, f, w, f, w},
            {w, w, w, w, w, w, w, w, w, w, f, w, w, w}
        };

        
        Room dummyRoom = new Room(dummy, new ColoredText(" "));
        Room[][] rooms = {
            {new Room(rdOpen, new ColoredText("R")), new Room(lrdOpen, new ColoredText("R")), new Room(ldOpen, new ColoredText("R")), dummyRoom},
            {new Room(udOpen, new ColoredText("R")), new Room(uOpen, new ColoredText("R")), new Room(udOpen, new ColoredText("R")), dummyRoom},
            {new Room(urOpen, new ColoredText("R")), new Room(lrOpen, new ColoredText("R")), new Room(ludOpen, new ColoredText("R")), dummyRoom},
            {new Room(dummy, new ColoredText("M")), new Room(rdMaze2x, new ColoredText("M")), new Room(lurOpen, new ColoredText("R")), new Room(ldOpen, new ColoredText("R"))},
            {new Room(dummy, new ColoredText("M")), new Room(dummy, new  ColoredText("M")), dummyRoom, new Room(udOpen, new ColoredText("R"))},
            {dummyRoom, new Room(udOpen, new ColoredText("R")), dummyRoom, new Room(uOpen, new ColoredText("R"))},
            {dummyRoom, new Room(urOpen, new ColoredText("R")), new Room(lrDoor, new ColoredText("D")), new Room(ldOpen, new ColoredText("R"))},
            {dummyRoom, dummyRoom, dummyRoom, new Room(udDoor, new ColoredText("D"))},
            {dummyRoom, new Room(rdOpen, new ColoredText("R")), new Room(lrOpen, new ColoredText("R")), new Room(ulOpen, new ColoredText("R"))},
            {dummyRoom, new Room(urOpen, new ColoredText("R")), new Room(lOpen, new ColoredText("R")), dummyRoom}
        };
        
        
        WorldMap world = new WorldMap(rooms, new ColoredText("World Map"));
        new Victory(3, 3, world.getRoom(2, 9));
        RoomLink.linkRooms(world.getRoom(1, 1), 3, 0, world.getRoom(1, 0), 3, 6);
        RoomLink.linkRooms(world.getRoom(1, 0), 0, 3, world.getRoom(0, 0), 6, 3);
        RoomLink.linkRooms(world.getRoom(1, 0), 6, 3, world.getRoom(2, 0), 0, 3);
        RoomLink.linkRooms(world.getRoom(0, 0), 3, 6, world.getRoom(0, 1), 3, 0);
        RoomLink.linkRooms(world.getRoom(2, 0), 3, 6, world.getRoom(2, 1), 3, 0);
        RoomLink.linkRooms(world.getRoom(0, 1), 3, 6, world.getRoom(0, 2), 3, 0);
        RoomLink.linkRooms(world.getRoom(2, 1), 3, 6, world.getRoom(2, 2), 3, 0);
        RoomLink.linkRooms(world.getRoom(0, 2), 6, 3, world.getRoom(1, 2), 0, 3);
        RoomLink.linkRooms(world.getRoom(1, 2), 6, 3, world.getRoom(2, 2), 0, 3);
        RoomLink.linkRooms(world.getRoom(2, 2), 3, 6, world.getRoom(2, 3), 3, 0);
        RoomLink.linkRooms(world.getRoom(2, 3), 0, 3, world.getRoom(1, 3), 13, 3);
        RoomLink.linkRooms(world.getRoom(2, 3), 6, 3, world.getRoom(3, 3), 0, 3);
        RoomLink.linkRooms(world.getRoom(3, 3), 3, 6, world.getRoom(3, 4), 3, 0);
        RoomLink.linkRooms(world.getRoom(3, 4), 3, 6, world.getRoom(3, 5), 3, 0);
        RoomLink.linkRooms(world.getRoom(1, 3), 10, 13, world.getRoom(1, 5), 3, 0);
        RoomLink.linkRooms(world.getRoom(1, 5), 3, 6, world.getRoom(1, 6), 3, 0);
        RoomLink.linkRooms(world.getRoom(1, 6), 6, 3, world.getRoom(2, 6), 0, 3);
        RoomLink.linkRooms(world.getRoom(2, 6), 6, 3, world.getRoom(3, 6), 0, 3);
        RoomLink.linkRooms(world.getRoom(3, 6), 3, 6, world.getRoom(3, 7), 3, 0);
        RoomLink.linkRooms(world.getRoom(3, 7), 3, 6, world.getRoom(3, 8), 3, 0);
        RoomLink.linkRooms(world.getRoom(3, 8), 0, 3, world.getRoom(2, 8), 6, 3);
        RoomLink.linkRooms(world.getRoom(2, 8), 0, 3, world.getRoom(1, 8), 6, 3);
        RoomLink.linkRooms(world.getRoom(1, 8), 3, 6, world.getRoom(1, 9), 3, 0);
        RoomLink.linkRooms(world.getRoom(2, 9), 0, 3, world.getRoom(1, 9), 6, 3);


        int missingKey = (int) (Math.random() * 3);
        if (missingKey != 0) new Key(1, 1, world.getRoom(0, 0));
        if (missingKey != 1) new Key(3, 3, world.getRoom(3, 5));
        if (missingKey != 2) new Key(12, 12, world.getRoom(1, 3));


        System.out.println("Enter your name: ");
        String name = IOUtils.getInput();
        Player player = new Player(3, 3, world.getRoom(1, 1), name);

        InputMap.init();
        boolean running = true;
        while (running) {
            player.drawRoom();
            InputMap.Action userInput = InputMap.mapFrom(IOUtils.getInput());
            switch (userInput) {
                case UP:
                    player.move(Direction.UP);
                    break;
                case DOWN:
                    player.move(Direction.DOWN);
                    break;
                case LEFT:
                    player.move(Direction.LEFT);
                    break;
                case RIGHT:
                    player.move(Direction.RIGHT);
                    break;
                case USE:
                    player.onUse();
                    break;
                case MAP:
                    String[] lines = world.toString().split("\n");
                    IOUtils.printInBox(20, 20, lines);
                    break;
				case ERROR:
                    System.out.println("That is not a valid input, try again");
                    break;
                case QUIT:
                    System.out.println(ColoredText.builder().green("Goodbye, ").then(player.getName()).build());
                    System.exit(0);
					break;
            }
        }
    }
}