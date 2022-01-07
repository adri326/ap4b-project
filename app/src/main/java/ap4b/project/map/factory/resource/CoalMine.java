package ap4b.project;

public class CoalMine extends ResourceGenerator{
    // TODO: make use of Tile::storage
    public CoalMine(Tile tile) {
        this(tile, 1000.0f);
    }

    public CoalMine(Tile tile, float q){
        super(tile);
        this.quantity = q;
        this.rawQuantity = 10000000;
        this.speed = 6;
    }

    public void generateResource() {
        if(rawQuantity>0) {
            this.quantity += 20.0f;
            --this.rawQuantity;
             this.storage.setStored(ResourceType.COAL, (int) quantity);
        }
        else
            System.out.println("La resource premiere est epuise, pensez à changer de ressource!! ");
    }

    public boolean hasRequiredResources(){return false;}

    public String getTexture() {
        return "mine-coal";
    }

    public String getBackgroundTexture() {
        return this.terrainType.getTexture();
    }
}
