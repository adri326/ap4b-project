package ap4b.project;

public class Restaurant extends Habitation
{
    public Restaurant(Tile tile) {
        super(tile);
        this.consommationUnity=20;
        this.taux_satisfaction=2;
        this.consomationSpeed=700;
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
