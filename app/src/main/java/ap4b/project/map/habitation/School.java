package ap4b.project;

public class School extends Habitation
{
    public School(Tile tile) {
        super(tile);
        this.consumptionFactor=30;
        this.satisfaction=3;
        this.consumptionFactor=500;
    }

    public void addPopulation() {
        population+=30;
    }

    public void reducePopulation() {
        population-=30;
    }

    @Override
    public String getTexture() {
        return "school";
    }
}
