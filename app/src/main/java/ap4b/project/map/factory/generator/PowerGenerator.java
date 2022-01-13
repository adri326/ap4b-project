package ap4b.project;

public abstract class PowerGenerator extends Factory{
    PowerGenerator(Tile tile) {
        super(tile);
    }

    public abstract void produce(Weather weather);
    public abstract boolean hasRequiredResources();
    public Upgrade getUpgrades(){
        // return upgrade;
        return null;
    }

}
