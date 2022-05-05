package world;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static int newX(int x, Direction dir) {
        if (dir == LEFT) {
            return x - 1;
        }
        if (dir == RIGHT) {
            return x + 1;
        }
        return x;
    }
    
    public static int newY(int y, Direction dir) {
        if (dir == UP) {
            return y - 1;
        }
        if (dir == DOWN) {
            return y + 1;
        }
        return y;
    }
}
