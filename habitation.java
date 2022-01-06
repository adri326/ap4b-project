package ap4b.project;

public class habitation extends Tile
{
	public int population;
    public int consommationUnity;
    public int nb_aliment;
    public int nb_non_aliment;
    public int consomationSpeed;
    public int taux_satisfaction;
    
    public habitation()
    {
    	population=1;
    	nb_aliment=0;
    	nb_non_aliment=0;
    }
    
    public void PayElectricity(int quantity,int price, Bank bank)
    {
    	bank.updateMoney(quantity*price);
    }
    
    public void updateSatisfaction()
    {
    	if(this.powered)
    	{
    		this.satisfaction+=(nb_aliment/(nb_non_aliment+nb_aliment))*population+taux_satisfaction;
    	}
    	else
    	{
    		this.satisfaction-=(nb_non_aliment/nb_aliment)*population+taux_satisfaction;
    	}
    }
    public void addPopulation() {
    	
    }
    
    public void reducePopulation() {
    	
    }  
    
    public void UpdateGeneration(Bank bank) // Usine qui alimente l'habitation
    {
        while(true)
        {
        	Thread.sleep(consomationSpeed);
        	int quantity=consommationUnity*population;
            if(this.storage.getStored()>quantity)
            {
            	nb_aliment++;
            	this.PayElectricity(quantity,this.storage.getPrice(),bank);
            	this.powered=true;
            }
            else
            {
            	this.powered=false;
            	nb_non_aliment++;
            }
            this.updateSatisfaction();
        }
    }
}
