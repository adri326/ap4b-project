package ap4b.project;

public abstract class ResourceGenerator extends Factory{
    public ResourceGenerator(Tile tile) {
        super(tile);
    }

    protected float quantity;
    protected long rawQuantity; //Contient la quantite maximal de la resource qui peut etre extrait

    public abstract void generateResource();
    public float getQuantity(){return this.quantity;}
    public Upgrade getUpgrades(){
        // return upgrade;
        return null;
    }
}
