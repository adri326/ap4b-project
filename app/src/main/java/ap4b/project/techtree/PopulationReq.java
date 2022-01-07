package ap4b.project;

// TODO : Ajouter un attribut population dans GameState

public class PopulationReq extends Requirement
{
    private int amount;

    public void PopulationReq(int minimumAmount)
    {
        amount=minimumAmount;
    }

    public boolean fulfilled(GameState state)
    {
        return false;
        // TODO: fix the following and remove the previous line
        // if (state.population>=amount)
        // {
        //     return true;
        // }
        // else
        // {
        //     return false;
        // }
    }
}
