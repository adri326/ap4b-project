package ap4b.project;

public class FusionGenerator extends PowerGenerator{
    public FusionGenerator(){
        super();
        this.speed = 5;
    }
    public FusionGenerator(Upgrade i){
        this.speed = 5;
        this.installedUpgrades = i;
    }
    public boolean hasRequiredResources(){return true;}
    public void produce(Weather whe){
          float temp = storage.getStored(ResourceType.ENERGY) + 100.0f;
          this.storage.setStored(ResourceType.ENERGY, (int) temp);
          this.pollution += 0.0005;
    }
}
