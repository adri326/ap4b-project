package ap4b.project;

public class MoneyReward implements Distributable {
    private int amount;

    public MoneyReward(){
        this.amount = 0;
    }
    public MoneyReward(int amount){
        this.amount = amount;
    }

    public void distribute(GameState state) {
        state.bank.updateMoney(this.amount);
    }
}
