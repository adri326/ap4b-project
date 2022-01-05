package ap4b.project;

public abstract class ResourceGenerator extends Factory{
    protected float quantity;
    protected long rawQuantity; //Contient la quantite maximal de la ressource qui peut etre extrait

    public abstract void generateResource();
    public float getQuantity(){return this.quantity;}
    public Upgrade getUpgrades(){
        Upgrade up = new Upgrade();
        return up;
    }
}
