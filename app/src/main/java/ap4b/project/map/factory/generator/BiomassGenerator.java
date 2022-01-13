package ap4b.project;

public class BiomassGenerator extends PowerGenerator{
    private WoodFactory woodF;

    public BiomassGenerator(Tile tile) {
        super(tile);
        this.speed = 4;
    }

    public boolean hasRequiredResources(){return true;}
    public Upgrade getUpgrades(){
        Upgrade up = new Upgrade();
        return up;
    }

    public void produce(Weather weather){
          if(woodF.quantity>0) {
              float temp = storage.getStored(ResourceType.WOOD) + 50.0f;
              this.storage.setStored(ResourceType.WOOD, (int) temp);
              this.pollution += 0.001;
              woodF.quantity-=30;
          }
          else
              System.out.println("Impossible de produre, la quantite du bois est insuffisante! ");
    }

    @Override
    public String getTexture() {
        return "biomass-generator";
    }
}
