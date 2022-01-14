package ap4b.project;

public class FusionGenerator extends PowerGenerator{
    public FusionGenerator(Tile tile){
        super(tile);
        this.speed = 5;
        this.storage.setMaxStored(ResourceType.ENERGY, 1000);
    }

    public boolean hasRequiredResources(){
        return true;
    }

    public void updateGeneration(GameState state){
        this.pollution += 0.001f;
        this.storage.setStored(ResourceType.ENERGY, this.storage.getStored(ResourceType.ENERGY) + 1000);
    }

    @Override
    public String getTexture() {
        return "fusion-generator";
    }
}
