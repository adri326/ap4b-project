package ap4b.project;

public class ResourceReq extends Requirement
{
    private int amount;
    private ResourceType resource;

    public void ResourceReq(int minimumAmount,ResourceType minimumResource)
    {
        amount=minimumAmount;
        resource=minimumResource;
    }

    public boolean fulfilled(GameState state)
    {
        if (state.totalResources(resource)>=amount)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
