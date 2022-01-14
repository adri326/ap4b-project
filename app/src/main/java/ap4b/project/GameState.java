package ap4b.project;

public class GameState {
    public float electricityPrice = 1.0f; // TODO: set to an actual value
    public Weather weather;
    public Map map;
    public Simulation simulation;
    public Bank bank;
    public TechTree techTree;

    // Ideally we wouldn't have this here, but because the connectors are busted we kind of have to go this route. - Shad
    public Storage globalStorage = new Storage();

    public GameState(int width, int height, int difficulty) {
        this.map = new Map(width, height);
        this.simulation = new Simulation(difficulty);
        this.weather = new Weather(12,  20,  10,  100, 200);
        this.bank = new Bank(100000,10000);
        this.techTree = new TechTree();

        for (ResourceType type : ResourceType.values()) {
            this.globalStorage.setMaxStored(type, 10000);
        }
        this.globalStorage.setMaxStored(ResourceType.ENERGY, 100000);
    }

    public void update() {
        System.out.println("== Update ==");
        this.weather.updateWeather();
        this.simulation.updatePopulation(this);
        this.simulation.updateGeneration(this);
        // TODO
        System.out.println(this.globalStorage.toString());
    }
}
