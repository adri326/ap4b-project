package ap4b.project;

public class Habitation extends Tile
{
    public int population = 1;
    public int consumptionFactor;
    public int lastPowered = 0;
    public float satisfaction = 0.0f;

    public Habitation(Tile tile) {
        super(tile);
    }

    public void payElectricity(int quantity, float price, Bank bank) {
        bank.updateMoney((int)((float)quantity*price));
    }

    public void updateSatisfaction() {
        float expectedSatisfaction = (float)this.lastPowered / (float)this.population;
        expectedSatisfaction *= 2.0f * (1.0f - sigmoid(this.pollution / 10.0f));

        this.satisfaction = (this.satisfaction + expectedSatisfaction) / 2.0f;
    }

    public void addPopulation() {

    }

    public void reducePopulation() {

    }

    public void updateGeneration(GameState state) {
        int quantity = consumptionFactor * population;
        int energyStored = this.storage.getStored(ResourceType.ENERGY);
        if (energyStored >= quantity) {
            this.lastPowered = population;
            this.storage.setStored(ResourceType.ENERGY, energyStored - quantity);
            this.payElectricity(quantity, state.electricityPrice, state.bank);
        } else {
            this.lastPowered = energyStored / consumptionFactor;
            this.storage.setStored(ResourceType.ENERGY, 0);
            this.payElectricity(energyStored, state.electricityPrice, state.bank);
        }
    }

    public String getTexture() {
        return "";
    }

    @Override
    public String getBackgroundTexture() {
        return "concrete";
    }

    private float sigmoid(float x) {
        // tan¯¹(x*π/2)/π+.5
        return (float)Math.atan(x * Math.PI / 2.0f) / (float)Math.PI + 0.5f;
    }
}
