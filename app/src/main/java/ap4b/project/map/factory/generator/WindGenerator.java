package ap4b.project;

public class WindGenerator extends PowerGenerator{
    public WindGenerator(Tile tile){
        super(tile);
        this.speed = 3;
    }

    public boolean hasRequiredResources(){return false;}

    public void produce(Weather weather){
        float temp = storage.getStored(ResourceType.ENERGY) + 0.1f * weather.getIrrigationFactor();
        this.storage.setStored(ResourceType.ENERGY, (int) temp);
        this.pollution += 0.0000000001;
    }

    @Override
    public String getTexture() {
        return "wind-generator";
    }
}
