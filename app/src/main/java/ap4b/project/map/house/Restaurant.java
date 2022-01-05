package ap4b.project;

// Temporary class, please delete once the real housing classes are added
class Restaurant extends Tile {
    public Restaurant(Tile tile) {
        super(tile);
    }

    @Override
    public String getTexture() {
        return "restaurant";
    }
}
