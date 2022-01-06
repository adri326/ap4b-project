package ap4b.project;

public class UraniumGenerator extends PowerGenerator{
    private UraniumMine uraM;

    public UraniumGenerator(){
        super();
        this.speed = 5;
    }
    public UraniumGenerator(Upgrade i){
        this.speed = 5;
        this.installedUpgrades = i;
    }
    public boolean hasRequiredResources(){return true;}
    public void produce(Weather whe){
        while (true) {
            try {
                Thread.sleep(speed*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(uraM.quantity>0) {
                float temp = storage.getStored(ResourceType.URANIUM) + 100.0f;
                this.storage.setStored(ResourceType.URANIUM, (int) temp);
                this.pollution += 0.009;
                uraM.quantity-=100;
            }
            else
                System.out.println("Impossible de produre, la quantite de l'uranium est insuffisante! ");

        }
    }
}
