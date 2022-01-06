package ap4b.project;

public class BiomassGenerator extends PowerGenerator{
    private WoodFactory woodF;

    public BiomassGenerator(){
        super();
        this.speed = 4;
    }
    public BiomassGenerator(Upgrade i){
        this.speed = 4;
        this.installedUpgrades = i;
    }


    public boolean hasRequiredResources(){return true;}
    public Upgrade getUpgrades(){
        Upgrade up = new Upgrade();
        return up;
    }

    public void produce(Weather whe){
        while (true) {
            try {
                Thread.sleep(speed*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(woodF.quantity>0) {
                float temp = storage.getStored(ResourceType.WOOD) + 50.0f;
                this.storage.setStored(ResourceType.WOOD, (int) temp);
                this.pollution += 0.001;
                woodF.quantity-=30;
            }
            else
                System.out.println("Impossible de produre, la quantite du bois est insuffisante! ");

        }
    }
}
