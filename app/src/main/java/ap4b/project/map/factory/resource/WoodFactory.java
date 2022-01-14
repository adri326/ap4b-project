package ap4b.project;

public class WoodFactory extends ResourceGenerator{
    public WoodFactory(Tile tile) {
        this(tile, 500.0f);
        this.storage.setMaxStored(ResourceType.WOOD, 1000);
    }

    public WoodFactory(Tile tile, float q) {
        super(tile);
        this.quantity = q;
        this.speed = 5;
    }
    public void updateGeneration(GameState state) {
        this.quantity += 100.0f;
        this.rawQuantity-=10;
        this.storage.setStored(ResourceType.WOOD, (int) this.quantity);
    }

    public boolean hasRequiredResources(){return false;}


    @Override
    public String getTexture() {
        return "wood-factory";
    }

    @Override
    public String getBackgroundTexture() {
        return this.terrainType.getTexture();
    }
}
