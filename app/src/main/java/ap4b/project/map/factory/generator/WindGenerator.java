package ap4b.project;

public class WindGenerator extends PowerGenerator{
    public WindGenerator(Tile tile){
        super(tile);
        this.speed = 3;
        this.storage.setMaxStored(ResourceType.ENERGY, 1000);
    }

    public boolean hasRequiredResources(){return false;}

    public void updateGeneration(GameState state){
        float temp = storage.getStored(ResourceType.ENERGY) + 0.1f * state.weather.getWindFactor();
        this.storage.setStored(ResourceType.ENERGY, (int) temp);
        this.pollution += 0.0000000001;
    }

    @Override
    public String getTexture() {
        return "wind-generator";
    }

    @Override
    public String getBackgroundTexture() {
        return this.terrainType.getTexture();
    }
}
