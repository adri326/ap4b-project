package ap4b.project;

public class Habitation extends Tile
{
    public int population = 1;
    public int consommationUnity;
    public int nb_aliment = 0;
    public int nb_non_aliment = 0;
    public int consomationSpeed;
    public int taux_satisfaction;

    public Habitation(Tile tile)
    {
        super(tile);
    }

    public void payElectricity(int quantity, float price, Bank bank)
    {
        bank.updateMoney((int)((float)quantity*price));
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

    public void updateGeneration(GameState state) // Usine qui alimente l'Habitation
    {
        // while(true)
        // {
        //     Thread.sleep(consomationSpeed);
            int quantity=consommationUnity*population;
            if(this.storage.getStored(ResourceType.ENERGY)>quantity)
            {
                nb_aliment++;
                this.payElectricity(quantity, state.electricityPrice, state.bank);
                this.powered=true;
            }
            else
            {
                this.powered=false;
                nb_non_aliment++;
            }
            this.updateSatisfaction();
        // }
    }
    public String getTexture() {
        return "";
    }
}
