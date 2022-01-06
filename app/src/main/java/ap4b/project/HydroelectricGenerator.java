package ap4b.project;

public class HydroelectricGenerator extends PowerGenerator{
    public HydroelectricGenerator(){
        super();
        this.speed = 7;
    }
    public HydroelectricGenerator(Upgrade i){
        this.speed = 7;
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

            float temp = storage.getStored(ResourceType.ENERGY) + 0.5f * whe.getIrrigationFactor();
            this.storage.setStored(ResourceType.ENERGY, (int) temp);
            this.pollution += 0.0000001;

        }
    }
}
