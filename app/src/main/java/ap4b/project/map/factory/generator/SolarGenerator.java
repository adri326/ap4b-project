package ap4b.project;

public class SolarGenerator extends PowerGenerator {
    public SolarGenerator(Tile tile){
        super(tile);
        this.speed = 3;
        this.storage.setMaxStored(ResourceType.ENERGY, 1000);
    }

    public boolean hasRequiredResources(){return false;}

    public void updateGeneration(GameState state){
        float temp = storage.getStored(ResourceType.ENERGY) + 0.1f * state.weather.getSunFactor();
        this.storage.setStored(ResourceType.ENERGY, (int) temp);
        this.pollution += 0.000001;
    }

    @Override
    public String getTexture() {
        return "solar-generator";
    }

    @Override
    public String getBackgroundTexture() {
        return this.terrainType.getTexture();
    }
}
