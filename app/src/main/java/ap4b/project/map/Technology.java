package ap4b.project;

public class Technology {
    private ResourceReward[] resourceReward;
    private MoneyReward[] moneyReward;
    private ResourceReq resourceReq;
    private MoneyReq moneyReq;
    private PreviousTechReq previousTechReq;
    private PopulationReq populationReq;
    private String name;

    public Technology(ResourceReward[] resourceReward, MoneyReward[] moneyReward, ResourceReq resourceReq, MoneyReq moneyReq, PreviousTechReq previousTechReq,PopulationReq populationReq, String name ){
        this.resourceReward = resourceReward;
        this.moneyReward = moneyReward;
        this.resourceReq= resourceReq;
        this.populationReq = populationReq;
        this.moneyReq = moneyReq;
        this.previousTechReq = previousTechReq;
        this.name = name;
    }

    public ResourceReward[] getResourceReward(){ return this.resourceReward; }
    public MoneyReward[] getMoneyReward(){ return this.moneyReward; }
    public String getName(){ return this.name; }

    public void setResourceReward(ResourceReward[] resourceReward){this.resourceReward = resourceReward;}
    public void setMoneyReward(MoneyReward[] moneyReward){this.moneyReward = moneyReward;}
    public void setName(String name){this.name = name;}

    public boolean canUnlock(Gamestate state){
        if(moneyReq.fulfilled(state) & resourceReq.fulfilled(state) & previousTechReq.fulfilled(state) & populationReq.fulfilled(state)) {
            return true;
        }
        else {
            return false;
        }
    }

}
