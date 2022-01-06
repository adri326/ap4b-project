package ap4b.project;

public class ThoriumGenerator extends PowerGenerator{
    private ThoriumMine thoM;

    public ThoriumGenerator(){
        super();
        this.speed = 5;
    }
    public ThoriumGenerator(Upgrade i){
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

            if(thoM.quantity>0) {
                float temp = storage.getStored(ResourceType.THORIUM) + 200.0f;
                this.storage.setStored(ResourceType.THORIUM, (int) temp);
                this.pollution += 0.001;
                thoM.quantity-=100;
            }
            else
                System.out.println("Impossible de produre, la quantite du thorium est insuffisante! ");

        }
    }
}
