package ap4b.project;

public class Hospital extends Habitation
{
    public Hospital(Tile tile) {
        super(tile);
        this.consommationUnity=50;
        this.satisfaction=5;
        this.consomationSpeed=100;
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
