package ap4b.project;

public class ThoriumMine extends ResourceGenerator{
    // TODO: make use of Tile::storage
    public ThoriumMine(Tile tile) {
        this(tile, 500.0f);
    }

    public ThoriumMine(Tile tile, float q){
        super(tile);
        this.quantity = q;
        this.rawQuantity = 50000000;
        this.speed = 6;
    }

    public void generateResource() {
        if (rawQuantity>0) {
            this.quantity += 200.0f;
            --this.rawQuantity;
            this.storage.setStored(ResourceType.THORIUM, (int) this.quantity);
        }
        else
            System.out.println("La resource premiere est epuise, pensez à changer de resource!! ");
    }

    public boolean hasRequiredResources(){return false;}

    public String getTexture() {
        return "mine-thorium";
    }

    public String getBackgroundTexture() {
        return this.terrainType.getTexture();
    }
}
