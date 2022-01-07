package ap4b.project;

public class UraniumMine extends ResourceGenerator{
    // TODO: make use of Tile::storage
    public UraniumMine(Tile tile) {
        this(tile, 1000.0f);
    }

    public UraniumMine(Tile tile, float q){
        super(tile);
        this.quantity = q;
        this.rawQuantity = 10000000;
        this.speed = 6;
    }

    public void generateResource() {
        if (rawQuantity>0) {
            this.quantity += 200.0f;
            --this.rawQuantity;
        }
        else
            System.out.println("La resource premiere est epuise, pensez à changer de resource!! ");
    }

    public boolean hasRequiredResources(){return false;}

    public String getTexture() {
        return "mine-uranium";
    }

    public String getBackgroundTexture() {
        return this.terrainType.getTexture();
    }
}
