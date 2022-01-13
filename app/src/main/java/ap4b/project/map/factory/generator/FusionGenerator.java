package ap4b.project;

public class FusionGenerator extends PowerGenerator{
    public FusionGenerator(Tile tile){
        super(tile);
        this.speed = 5;
    }

    public boolean hasRequiredResources(){return true;}
    public void produce(Weather weather){
          float temp = storage.getStored(ResourceType.ENERGY) + 100.0f;
          this.storage.setStored(ResourceType.ENERGY, (int) temp);
          this.pollution += 0.0005;
    }

    @Override
    public String getTexture() {
        return "fusion-generator";
    }
}
