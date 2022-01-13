package ap4b.project;

public class CoalGenerator extends PowerGenerator{
    private CoalMine coalM;

    public CoalGenerator(Tile tile){
        super(tile);
        this.speed = 5;
    }

    public boolean hasRequiredResources(){return true;}
    public void produce(Weather weather){
          if(coalM.quantity>0) {
              float temp = storage.getStored(ResourceType.COAL) + 1.0f;
              this.storage.setStored(ResourceType.COAL, (int) temp);
              this.pollution += 0.001;
              coalM.quantity-=10;
          }
          else
              System.out.println("Impossible de produre, la quantite du charbon est insuffisante! ");
    }

    @Override
    public String getTexture() {
        return "coal-generator";
    }
}
