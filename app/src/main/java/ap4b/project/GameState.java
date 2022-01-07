package ap4b.project;

public class GameState {
    public float electricityPrice = 1.0f; // TODO: set to an actual value
    public Weather weather;
    public Map map;
    public Simulation simulation;
    public Bank bank;
    public TechTree techTree;
    public ap4b.project.Tile tile;
    public ap4b.project.Habitation habitation;
    public ap4b.project.Textures textures;
    public ap4b.project.Hospital hospital;
    public ap4b.project.Restaurant restaurant;
    public ap4b.project.School school;
    public ap4b.project.SimpleHousing simpleHousing;
    public ap4b.project.TownHall townHall;

    public GameState(int width, int height, int difficulty) {
        this.map = new Map(width, height);
        this.simulation = new Simulation(difficulty);
        this.weather = new Weather(12,  20,  10,  100, 200);
        this.bank = new Bank(100000,10000);
        this.techTree = new TechTree();
        this.tile = new ap4b.project.Tile();
        this.habitation =new ap4b.project.Habitation(tile);
        this.hospital = new ap4b.project.Hospital(tile);
        this.restaurant = new ap4b.project.Restaurant(tile);
        this.school = new ap4b.project.School(tile);
        this.simpleHousing = new ap4b.project.SimpleHousing(tile);
        this.townHall = new ap4b.project.TownHall(tile);
    }

    public void update() {
        // TODO
    }
}
