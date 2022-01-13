package ap4b.project;

public class WoodFactory extends ResourceGenerator{
    public WoodFactory(Tile tile) {
        this(tile, 500.0f);
    }

    public WoodFactory(Tile tile, float q) {
        super(tile);
        this.quantity = q;
        this.speed = 5;
    }
    public void generateResource() {
      if (rawQuantity>0) {
          this.quantity += 100.0f;
          this.rawQuantity-=10;
          this.storage.setStored(ResourceType.WOOD, (int) this.quantity);
        }
        else
            System.out.println("La resource premiere est epuise, pensez à changer de resource!! ");
    }

    public boolean hasRequiredResources(){return false;}


    @Override
    public String getTexture() {
        return "wood-factory";
    }

    @Override
    public String getBackgroundTexture() {
        return this.terrainType.getTexture();
    }
}
