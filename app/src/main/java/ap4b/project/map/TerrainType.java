package ap4b.project;

public enum TerrainType {
    // TODO: individual textures
    PLAINS("grass"),
    RIVER("water"),
    HILLS("hills"),
    FOREST("forest"),
    COAL_FIELD("coal"),
    URANIUM_FIELD("uranium"),
    THORIUM_FIELD("thorium");

    private String texture;

    TerrainType(String texture) {
        this.texture = texture;
    }

    public String getTexture() {
        return this.texture;
    }
}
