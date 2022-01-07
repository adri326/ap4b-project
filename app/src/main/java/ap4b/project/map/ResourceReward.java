package ap4b.project;

public class ResourceReward implements Distributable{
    private int amount;
    private ResourceType resource;

    public ResourceReward(){
        this.amount = 0;
        this.resource = ResourceType.ENERGY;
    }
    public ResourceReward(int amount, ResourceType resource){
        this.amount = amount;
        this.resource = resource;
    }

    public void distribute(Gamestate gamestate) {
        gamestate.totalResources += this.amount;
    }
}
