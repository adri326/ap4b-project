package ap4b.project;

public abstract class PowerGenerator extends Factory{

    public abstract void produce(Weather whe);
    public abstract boolean hasRequiredResources();
    public Upgrade getUpgrades(){
        Upgrade up = new Upgrade();
        return up;
    }

}
