package ap4b.project;

// Temporary class, please delete once the real housing classes are added
class SimpleHousing extends Tile {
    public SimpleHousing(Tile tile) {
        super(tile);
    }

    @Override
    public String getTexture() {
        return "simplehousing";
    }
}
