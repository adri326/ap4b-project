package ap4b.project;

import java.util.HashMap;

public class Storage 
{
    private HashMap<ResourceType, Integer> stored = new HashMap<ResourceType, Integer>();
    private HashMap<ResourceType, Integer> maxStored = new HashMap<ResourceType, Integer>();
    
    public int getStored(ResourceType restype)
    {
        return stored.get(restype);
    }
    
    public int getMaxStored(ResourceType restype)
    {
        return maxStored.get(restype);
    }
    
    public void setStored(ResourceType restype, int value)
    {
        stored.put(restype,value);
    }
    public void setMaxStored(ResourceType restype, int value)
    {
        maxStored.put(restype,value);
    }
}
