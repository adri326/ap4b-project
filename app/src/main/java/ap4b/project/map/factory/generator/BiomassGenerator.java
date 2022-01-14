package ap4b.project;

public class BiomassGenerator extends PowerGenerator{
    private WoodFactory woodF;

    public BiomassGenerator(Tile tile) {
        super(tile);
        this.speed = 4;
        this.storage.setMaxStored(ResourceType.WOOD, 200);
        this.storage.setMaxStored(ResourceType.ENERGY, 400);
    }

    public boolean hasRequiredResources(){
        return this.storage.getStored(ResourceType.WOOD) > 0;
    }

    public void updateGeneration(GameState state){
        int stored = this.storage.getStored(ResourceType.WOOD);
        if (stored >= 30) {
            this.storage.setStored(ResourceType.URANIUM, stored - 30);
            this.pollution += 0.009f;
            this.storage.setStored(ResourceType.ENERGY, this.storage.getStored(ResourceType.ENERGY) + 50);
        }
    }

    @Override
    public String getTexture() {
        return "biomass-generator";
    }
}
