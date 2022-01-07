package ap4b.project;

public abstract class Connector extends Tile {
    Connector(Tile tile) {
        super(tile);
    }
    public abstract void updateNetwork(GameState state);
    public abstract boolean transportsResource(ResourceType resource);
    public abstract int getPrice();
}
