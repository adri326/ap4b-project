package ap4b.project;

public class CoalGenerator extends PowerGenerator{
    public CoalGenerator(Tile tile){
        super(tile);
        this.speed = 5;
        this.storage.setMaxStored(ResourceType.COAL, 200);
        this.storage.setMaxStored(ResourceType.ENERGY, 400);
    }

    public boolean hasRequiredResources(){
        return this.storage.getStored(ResourceType.COAL) > 0;
    }

    public void updateGeneration(GameState state){
        int stored = this.storage.getStored(ResourceType.COAL);
        if (stored >= 10) {
            this.storage.setStored(ResourceType.COAL, stored - 10);
            this.pollution += 0.4f;
            this.storage.setStored(ResourceType.ENERGY, this.storage.getStored(ResourceType.ENERGY) + 160);
        }
    }

    @Override
    public String getTexture() {
        return "coal-generator";
    }
}
