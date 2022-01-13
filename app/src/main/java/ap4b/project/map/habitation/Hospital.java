package ap4b.project;

public class Hospital extends Habitation
{
    public Hospital(Tile tile) {
        super(tile);
        this.consumptionFactor=50;
        this.satisfaction=5;
        this.consumptionFactor=100;
    }

    public void addPopulation() {
        population+=50;
    }

    public void reducePopulation() {
        population-=50;
    }

    @Override
    public String getTexture() {
        return "hospital";
    }
}
