package ap4b.project;

public class UraniumGenerator extends PowerGenerator{
    public UraniumGenerator(Tile tile){
        super(tile);
        this.speed = 5;
        this.storage.setMaxStored(ResourceType.URANIUM, 200);
        this.storage.setMaxStored(ResourceType.ENERGY, 400);
    }

    public boolean hasRequiredResources(){
        return this.storage.getStored(ResourceType.URANIUM) > 0;
    }

    public void updateGeneration(GameState state){
        int stored = this.storage.getStored(ResourceType.URANIUM);
        if (stored > 1) {
            this.storage.setStored(ResourceType.URANIUM, stored - 1);
            this.pollution += 0.009f;
            this.storage.setStored(ResourceType.ENERGY, this.storage.getStored(ResourceType.ENERGY) + 100);
        }
    }

    @Override
    public String getTexture() {
        return "uranium-generator";
    }
}
