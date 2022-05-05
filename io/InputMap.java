package io;

import java.util.HashMap;

public class InputMap {
    public enum Action {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        USE,
        ERROR,
        QUIT,
        MAP
    }

    public static HashMap<String, Action> map = new HashMap<String, Action>();

    public static void init() {
        setMappedString("w", Action.UP);
        setMappedString("s", Action.DOWN);
        setMappedString("a", Action.LEFT);
        setMappedString("d", Action.RIGHT);
        setMappedString("e", Action.USE);
        setMappedString("q", Action.QUIT);
        setMappedString("quit", Action.QUIT);
        setMappedString("m", Action.MAP);
        setMappedString("map", Action.MAP);
    }

    public static void setMappedString(String inp, Action act) {
        map.put(inp, act);
    }

    public static Action mapFrom(String inp) {
        if (map.containsKey(inp.toLowerCase())) {
            return map.get(inp.toLowerCase());
        }
        return Action.ERROR;
    }
}
