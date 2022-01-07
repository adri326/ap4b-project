package ap4b.project;

// TODO : Ajouter un attribut population dans GameState

public class PopulationReq extends Requirement
{
	private int amount;
	
	public void PopulationReq(int minimumAmount)
	{
		amount=minimumAmount;
	}
	
	public boolean	fulfilled(GameState state)
	{
		if (state.population>=amount)
		{
			return true;
		}
		else
		{
			return false;
		}
	}	
}
