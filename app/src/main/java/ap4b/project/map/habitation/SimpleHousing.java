package ap4b.project;

public class SimpleHousing extends Habitation
{
    public SimpleHousing(Tile tile) {
        super(tile);
        this.consumptionFactor=10;
        this.satisfaction=1;
        this.consumptionFactor=1000;
    }

    public void addPopulation() {
        population+=10;
    }

    public void reducePopulation() {
        population-=10;
    }

    @Override
    public String getTexture() {
        return "simplehousing";
    }
}
