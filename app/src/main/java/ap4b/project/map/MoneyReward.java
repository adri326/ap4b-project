package ap4b.project;

public class MoneyReward implements Distributable {
    private int amount;

    public ResourceReward(){
        this.amount = 0;
    }
    public ResourceReward(int amount){
        this.amount = amount;
    }

    public void distribute(Gamestate state) {
        gamestate.money += this.amount;
    }
}
