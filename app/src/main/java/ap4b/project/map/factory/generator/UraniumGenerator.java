package ap4b.project;

public class UraniumGenerator extends PowerGenerator{
    public UraniumGenerator(Tile tile){
        super(tile);
        this.speed = 5;
    }

    public boolean hasRequiredResources(){return true;}
    public void produce(Weather weather){
        // TODO: fix
        //  if(uraM.quantity>0) {
        //      float temp = storage.getStored(ResourceType.URANIUM) + 100.0f;
        //      this.storage.setStored(ResourceType.URANIUM, (int) temp);
        //      this.pollution += 0.009;
        //      uraM.quantity-=100;
        //  }
        //  else
        //     System.out.println("Impossible de produre, la quantite de l'uranium est insuffisante! ");
    }

    @Override
    public String getTexture() {
        return "uranium-generator";
    }
}
