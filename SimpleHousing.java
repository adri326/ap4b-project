package ap4b.project;

public class SimpleHousing extends habitation
{
	public SimpleHousing()
	{
		this.consommationUnity=10;
		this.taux_satisfaction=1;
		this.consomationSpeed=1000;
	}
	public void addPopulation() {
    	population+=10;
    }
    
    public void reducePopulation() {
    	population-=10;
    }
}
