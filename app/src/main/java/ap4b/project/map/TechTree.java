package ap4b.project;
import java.util.HashMap; // import the HashMap class
import java.util.*;

public class TechTree 
{
	private HashMap<String, Boolean> unlocked = new HashMap<String, Boolean>();
	private Technology technologies[];
	
	public boolean hasUnlocked(String tech)
	{
		return unlocked.get(tech);
	}
	
	public void setUnlocked(String tech, boolean value)
	{
		unlocked.remove(tech);
		unlocked.put(tech,value);
	}
	
	public Technology[] unlockables(GameState state)
	{
		List<Technology> techunlockable = new ArrayList<Technology>();
		int i;
		for(i=0;i<technologies.length;i++)
		{
			if(technologies[i].canUnlock(state))
			{
				techunlockable.add(technologies[i]);
			}
		}
	}
}
