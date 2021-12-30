package ap4b.project;

public class GameState {
    public float electricityPrice = 1.0f; // TODO: set to an actual value
    public Weather weather;
    public Map map;
    public Simulation simulation;
    public Bank bank;
    public TechTree techTree;

    public GameState(int width, int height, int difficulty) {
        this.map = new Map(width, height);
        this.simulation = new Simulation(difficulty);
        this.weather = new Weather();
        this.bank = new Bank();
        this.techTree = new TechTree();
    }

    public void update() {
        // TODO
    }
}
