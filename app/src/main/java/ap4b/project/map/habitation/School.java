package ap4b.project;

public class School extends Habitation
{
    public School(Tile tile) {
        super(tile);
        this.consommationUnity=30;
        this.satisfaction=3;
        this.consomationSpeed=500;
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
