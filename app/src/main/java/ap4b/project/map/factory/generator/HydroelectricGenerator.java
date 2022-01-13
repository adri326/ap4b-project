package ap4b.project;

public class HydroelectricGenerator extends PowerGenerator{
    public HydroelectricGenerator(Tile tile){
        super(tile);
        this.speed = 7;
    }

    public boolean hasRequiredResources(){return false;}

    public void produce(Weather weather){
        float temp = storage.getStored(ResourceType.ENERGY) + 0.5f * weather.getIrrigationFactor();
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
