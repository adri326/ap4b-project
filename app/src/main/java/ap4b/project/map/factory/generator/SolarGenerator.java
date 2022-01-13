package ap4b.project;

public class SolarGenerator extends PowerGenerator {
    public SolarGenerator(Tile tile){
        super(tile);
        this.speed = 3;
    }

    public boolean hasRequiredResources(){return false;}

    public void produce(Weather weather){
        float temp = storage.getStored(ResourceType.ENERGY) + 0.1f * weather.getSunFactor();
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
