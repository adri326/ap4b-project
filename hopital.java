package ap4b.project;

public class hopital extends habitation
{
	
	public hopital()
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
