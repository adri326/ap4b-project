package ap4b.project;

public abstract class Factory extends Tile {
    protected long speed;
    public Upgrade installedUpgrades;

    public abstract boolean hasRequiredResources();
    public abstract Upgrade getUpgrades();

}
