package ap4b.project;

import java.util.HashMap;

public class Storage
{
    private HashMap<ResourceType, Integer> stored = new HashMap<ResourceType, Integer>();
    private HashMap<ResourceType, Integer> maxStored = new HashMap<ResourceType, Integer>();

    public Storage() {
        for (ResourceType type : ResourceType.values()) {
            stored.put(type, 0);
            maxStored.put(type, 0);
        }
    }

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
        Integer max = this.maxStored.get(restype);
        if (value > max) {
            value = max;
        } else if (value < 0) {
            value = 0;
        }
        stored.put(restype,value);
    }

    public void setMaxStored(ResourceType restype, int value)
    {
        maxStored.put(restype,value);
    }

    public void transfer(ResourceType type, Storage into) {
        int value = this.stored.get(type);
        int newValue = into.stored.get(type) + value;
        int max = into.maxStored.get(type);
        if (newValue > max) {
            into.stored.put(type, (int)max);
            stored.put(type, (int)(newValue - max));
        } else {
            into.stored.put(type, (int)newValue);
            stored.put(type, 0);
        }
    }

    public void transferAll(Storage into) {
        for (ResourceType type : ResourceType.values()) {
            this.transfer(type, into);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (ResourceType type : ResourceType.values()) {
            if (!first) {
                sb.append(", ");
            }
            first = false;
            sb.append(type.name());
            sb.append(": [");
            sb.append(this.stored.get(type));
            sb.append(" / ");
            sb.append(this.maxStored.get(type));
            sb.append("]");
        }
        return sb.toString();
    }
}
