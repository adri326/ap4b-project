package ap4b.project;

public class Tile {
    public float pollution = 0.0f;
    public int beauty = 0; // TOOD: remove if not needed.
    public float satisfaction = 0.0f;
    public boolean powered = false;
    public TerrainType terrainType = TerrainType.PLAINS;
    public Storage storage;

    public Tile() {
        this.storage = new Storage();
    }

    /// Copies all of the Tile-properties into this instance
    public Tile(Tile tile) {
        this.pollution = tile.pollution;
        this.beauty = tile.beauty;
        this.satisfaction = tile.satisfaction;
        this.powered = tile.powered;
        this.terrainType = tile.terrainType;
        this.storage = tile.storage;
    }

    public void updateSatisfaction(float electricityPrice) {
        // noop
    }

    public float updatePollution() {
        // noop
        return this.pollution;
    }

    public void updateGeneration(GameState state) {
        // noop
    }

    public String getTexture() {
        return "";
    }

    public String getBackgroundTexture() {
        return this.terrainType.getTexture();
    }
}
