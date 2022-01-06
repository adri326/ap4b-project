package ap4b.project;

public abstract class Connector extends Tile {
    public abstract void updateNetwork();
    public abstract boolean transportsResource();
    public abstract int getPrice();
}
