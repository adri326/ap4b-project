package ap4b.project;

public class Road extends Connector {
    public Road(Tile tile) {
        super(tile);
    }

    public void updateNetwork(GameState state) {
        // TODO: see which buildings are connected and store that list in GameState
    }

    public boolean transportsResource(ResourceType resource) {
        return resource != ResourceType.ENERGY;
    }

    public int getPrice() {
        return 5;
    }
}
