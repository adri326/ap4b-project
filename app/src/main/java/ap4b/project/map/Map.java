package ap4b.project;

/**
    Class invariants:
    - width and height are positive
    - width and height correspond to the dimension of tiles
**/
public class Map {
    public final int width;
    public final int height;
    public Tile[][] tiles;

    public Map(int width, int height) {
        if (width <= 0) throw new Error("Invalid value for width: expected a positive number, got " + width);
        if (height <= 0) throw new Error("Invalid value for height: expected a positive number, got " + height);

        this.width = width;
        this.height = height;
        this.tiles = new Tile[height][width];
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && x < this.width && y >= 0 && y < this.height;
    }

    public boolean placeBuilding(int x, int y, Tile building) {
        if (!this.inBounds(x, y)) return false;
        // TODO

        return false;
    }

    public boolean destroyBuilding(int x, int y) {
        if (!this.inBounds(x, y)) return false;
        // TODO

        return false;
    }

    public boolean upgradeBuilding(int x, int y, String upgradeName) {
        if (!this.inBounds(x, y)) return false;
        // TODO

        return false;
    }
}
