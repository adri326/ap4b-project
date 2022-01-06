package ap4b.project;

public class Connector extends Tile{
    private int networkID;

    public abstract void updateNetwork();
    public abstract boolean transportsResource();
    public abstract static int getPrice();

}
