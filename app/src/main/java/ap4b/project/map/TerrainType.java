package ap4b.project;

public enum TerrainType {
    // TODO: individual textures
    PLAINS("grass"),
    RIVER("grass"),
    HILLS("grass"),
    FOREST("forest"),
    COAL_FIELD("grass"),
    URANIUM_FIELD("grass"),
    THORIUM_FIELD("grass");

    private String texture;

    TerrainType(String texture) {
        this.texture = texture;
    }

    public String getTexture() {
        return this.texture;
    }
}
