package ap4b.project;

public class Hospital extends Habitation
{
    
    public Hospital()
    {
        this.consommationUnity=50;
        this.taux_satisfaction=5;
        this.consomationSpeed=100;
    }
    public void addPopulation() {
        population+=50;
    }
    
    public void reducePopulation() {
        population-=50;
    }
}
