package ap4b.project;

public abstract class Factory extends Tile {
    // TODO: remove the following constructor:
    public Factory() {
        super();
    }

    public Factory(Tile tile) {
        super(tile);
    }

    protected long speed;
    public Upgrade installedUpgrades;

    public abstract boolean hasRequiredResources();
    public abstract Upgrade getUpgrades();

    @Override
    public String getBackgroundTexture() {
        return "concrete";
    }
}
