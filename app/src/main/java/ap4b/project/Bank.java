package ap4b.project;

// import groovy.console.ui.AstBrowser;

/*
Mon idée est la suivante :
fixez d'abord une certaine limite de crédit,
 en cas de problème, choisissez de contracter un prêt,
 la banque décidera de vous prêter en fonction du crédit,
 après le prêt, remboursez le prêt et augmentez en même temps le crédit,
 le prêt peut être remboursé en totalité ou en partie.
*/
public class Bank {
    private boolean loan = false;
    private int money = 0; // TODO: set to initial amount ~ use difficulty?
    private boolean hasDifficulty = false;
    private int loanmoney = 0;
    private int credit = 0;

    public Bank(){
        this.money = 0;
        this.credit = 0;
        this.loanmoney = this.money;
        this.loan = false;
    }

    public Bank(int money,  int credit) {
        this.money = money;
        this.credit = credit;
        this.loanmoney = this.money;
        this.loan = true;
        //Parce qu'au début, nous avons emprunté tout notre argent à la banque
        System.out.println("Situation initiale, Vous avez de l'argent "+this.money+"euros");
        System.out.println("Votre valeur de crédit initiale est de "+this.credit+"euros");
        System.out.println("Vous avez de prêt "+this.loanmoney+"euros");
    }

    public void updatecredit(int repayamountloan){
        this.credit += repayamountloan;
        System.out.println("Votre valeur de crédit maintenant est de "+this.credit+"eoros");
    }

    public int updateMoney(int amount) {
        if(amount < 0){
            throw new IllegalArgumentException();
        } else {
            this.money += amount;
            System.out.println("Vous avez de l'argent "+this.money+"euros");
            return this.money;
        }
    }

    public void systemeloan(int amount){
        if (hasDifficulty) {
            if (verifierCredit(amount)) {
                updateLoan();
                loanmoney += amount;
                this.money += amount;
                System.out.println("Succès des prêts");
                System.out.println("Vous avez de l'argent "+this.money+"euros");
                System.out.println("Vous avez de Nous avons des prêts "+this.loanmoney+"euros");
            } else {
                System.out.println("Désolé, votre cote de crédit actuelle n'est pas suffisante pour vous permettre d'emprunter ce prêt.");
            }
        } else {
            System.out.println("Vous êtes actuellement dans une bonne situation financière et n'avez pas besoin d'un prêt.");
        }
    }

    public boolean verifierCredit(int amount){
        if(amount <= this.credit*10)
            return true;
        else {
            return false;
        }
    }

    public void repayLoanMoney(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        } else {
            if (hasLoan()) {
                if (amount <= loanmoney) {
                    this.money -= amount;
                    updatecredit(amount);
                    this.loanmoney -= amount;
                    System.out.println("Vous avez de prêt "+this.loanmoney+"euros");
                } else {
                    this.money -= loanmoney;
                    updatecredit(loanmoney);
                    this.loanmoney = 0;
                    repayLoan();
                    System.out.println("Vous avez de prêt "+this.loanmoney+"euros");
                }
            } else {
                System.out.println("Vous n'avez actuellement aucun prêt à rembourser");
            }
        }
    }

    public int getMoney() {return this.money;}

    public void updateHasDifficulty(boolean difficulty){this.hasDifficulty = difficulty;}

    public boolean hasLoan() {
        return this.loan;
    }

    public void updateLoan(){
        this.loan = true;
    }
    public void repayLoan() {
        this.loan = false;
    }
    public void situationfiance(){
        System.out.println("Maintenant,Vous avez l'argent "+this.money+"euros");
        System.out.println("Et vous avez de prêt "+this.loanmoney+"euros");
    }
    /*
    public static void main(String[] args){
        Bank bank = new Bank(300,300);
        bank.updateHasDifficulty(true);
        bank.updateMoney(200);
        bank.repayLoanMoney(200);
        bank.systemeloan(200);
        bank.updateMoney(5000);
        bank.repayLoanMoney(600);
        bank.updateHasDifficulty(false);
        bank.systemeloan(300);
        bank.situationfiance();
    }*/
}
