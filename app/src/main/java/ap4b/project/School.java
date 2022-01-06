package ap4b.project;

public class School extends habitation 
{
    public School()
    {
        this.consommationUnity=30;
        this.taux_satisfaction=3;
        this.consomationSpeed=500;
    }
    public void addPopulation() {
        population+=30;
    }
    
    public void reducePopulation() {
        population-=30;
    }
}
