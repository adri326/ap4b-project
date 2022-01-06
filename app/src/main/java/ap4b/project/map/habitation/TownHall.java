package ap4b.project;

public class TownHall extends Habitation
{
    public TownHall(Tile tile) {
        super(tile);
        this.consommationUnity=40;
        this.taux_satisfaction=4;
        this.consomationSpeed=300;
    }

    public void addPopulation() {
        population+=40;
    }

    public void reducePopulation() {
        population-=40;
    }
}