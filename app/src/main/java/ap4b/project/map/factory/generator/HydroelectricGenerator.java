package ap4b.project;

public class HydroelectricGenerator extends PowerGenerator{
    public HydroelectricGenerator(Tile tile){
        super(tile);
        this.speed = 7;
        this.storage.setMaxStored(ResourceType.ENERGY, 1000);
    }

    public boolean hasRequiredResources(){return false;}

    public void updateGeneration(GameState state){
        float temp = storage.getStored(ResourceType.ENERGY) + 0.5f * state.weather.getIrrigationFactor();
        this.storage.setStored(ResourceType.ENERGY, (int) temp);
        this.pollution += 0.0000001;
    }

    @Override
    public String getTexture() {
        return "hydroelectric-generator";
    }

    @Override
    public String getBackgroundTexture() {
        return this.terrainType.getTexture();
    }
}
