package ap4b.project;

public class MoneyReq extends Requirement
{
    private int amount;

    public void MoneyReq(int minimumAmount)
    {
        amount=minimumAmount;
    }

    public boolean fulfilled(GameState state)
    {
        if (state.bank.getMoney()>=amount)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
