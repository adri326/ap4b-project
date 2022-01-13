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

        // Temporary, do not hesitate to remove this version of the worldgen
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.tiles[y][x] = new Tile();
                this.tiles[y][x].terrainType = Math.random() < 0.8 ? TerrainType.PLAINS : (
                    Math.random() < 0.5 ? TerrainType.HILLS : TerrainType.FOREST
                );
            }
        }

        for (int n = 0; n < width * height / 25; n++) {
            if (Math.random() < 0.75) {
                int x = (int)(Math.random() * this.width);
                int y = (int)(Math.random() * this.height);
                this.tiles[y][x].terrainType = Math.random() < 0.4 ? TerrainType.COAL_FIELD : (
                    Math.random() < 0.5 ? TerrainType.URANIUM_FIELD : TerrainType.THORIUM_FIELD
                );
            }
        }

        int x = (int)(Math.random() * this.width);
        int y = 0;
        while (y < this.height) {
            this.tiles[y][x].terrainType = TerrainType.RIVER;
            if (Math.random() < 0.6) {
                y += 1;
            } else {
                x += Math.random() < 0.5 ? 1 : -1;
            }
            if (x < 0) x = 0;
            if (x >= this.width) x = this.width - 1;
        }

        // this.tiles[3][3] = new Hospital(this.tiles[3][3]);
        // this.tiles[4][3] = new Restaurant(this.tiles[4][3]);
        // this.tiles[3][4] = new SimpleHousing(this.tiles[3][4]);
        // this.tiles[3][5] = new School(this.tiles[3][5]);

        // this.tiles[10][10] = new CoalMine(this.tiles[10][10]);
        // this.tiles[11][10] = new ThoriumMine(this.tiles[11][10]);
        // this.tiles[12][10] = new UraniumMine(this.tiles[12][10]);
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && x < this.width && y >= 0 && y < this.height;
    }

    // May return null
    public Tile get(int x, int y) {
        if (inBounds(x, y)) {
            return this.tiles[y][x];
        } else {
            return null;
        }
    }

    public boolean placeBuilding(int x, int y, Tile building) {
        if (!this.inBounds(x, y)) return false;

        this.tiles[y][x] = building;

        return false;
    }

    public boolean destroyBuilding(int x, int y) {
        if (!this.inBounds(x, y)) return false;

        this.tiles[y][x] = new Tile(this.tiles[y][x]);

        return false;
    }

    public boolean upgradeBuilding(int x, int y, String upgradeName) {
        if (!this.inBounds(x, y)) return false;
        // TODO

        return false;
    }
}
