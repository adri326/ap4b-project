package ap4b.project;

public class WindGenerator extends PowerGenerator{

    public WindGenerator(){
        super();
        this.speed = 3;
    }
    public WindGenerator(Upgrade i){
        this.speed = 3;
        this.installedUpgrades = i;
    }


    public boolean hasRequiredResources(){return false;}

    public void produce(Weather whe){
        while (true) {
            try {
                Thread.sleep(speed*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            float temp = storage.getStored(ResourceType.ENERGY) + 0.1f * whe.getIrrigationFactor();
            this.storage.setStored(ResourceType.ENERGY, (int) temp);
            this.pollution += 0.0000000001;

        }
    }
}
