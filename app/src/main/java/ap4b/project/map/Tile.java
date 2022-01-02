package ap4b.project;

public class Tile {
    public float pollution = 0.0f;
    public int beauty = 0; // TOOD: remove if not needed.
    public float satisfaction = 0.0f;
    public boolean powered = false;
    public TerrainType terrainType = TerrainType.PLAINS;
    public Storage storage;

    public void updateSatisfaction(float electricityPrice) {
        // noop
    }

    public float updatePollution() {
        // noop
        return this.pollution;
    }

    public float updateGeneration(Weather weather) {
        // noop
        return 0.0f;
    }

    public String getTexture() {
        return "";
    }
}
