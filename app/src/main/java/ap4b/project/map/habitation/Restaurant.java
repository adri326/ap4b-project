package ap4b.project;

public class Restaurant extends Habitation
{
    public Restaurant(Tile tile) {
        super(tile);
        this.consumptionFactor=20;
        this.satisfaction=2;
        this.consumptionFactor=700;
    }

    public void addPopulation() {
        population+=20;
    }

    public void reducePopulation() {
        population-=20;
    }

    @Override
    public String getTexture() {
        return "restaurant";
    }
}
