package ap4b.project;

public class PreviousTechReq extends Requirement
{
    private String tech;
    
    public void PreviousTechReq(int minimumTech)
    {
        tech=minimumTech;
    }
    
    public boolean fulfilled(GameState state)
    {
        if (state.techTree.hasunlocked(tech))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
