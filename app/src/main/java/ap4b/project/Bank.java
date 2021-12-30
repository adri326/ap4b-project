package ap4b.project;

public class Bank {
    private boolean loan = false;
    private int money = 0; // TODO: set to initial amount ~ use difficulty?

    public Bank() {
        // TODO
    }

    public int updateMoney(int amount) {
        return this.money += amount;
    }

    public int getMoney() {
        return this.money;
    }

    public boolean hasLoan() {
        return this.loan;
    }

    public void repayLoan() {
        this.loan = false;
    }
}
