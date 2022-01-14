package ap4b.project;
import javafx.scene.image.Image;
import java.util.Random;
import java.util.HashMap;

public class Simulation {
    public int difficulty;
    private float totalSatisfaction = 0.0f;
    private int totalPopulation = 0;
    private Random rd = new Random();

    public Simulation(int difficulty) {
        this.difficulty = difficulty;
    }

    public void updatePopulation(GameState state) {
        this.updateSatisfaction(state);

        float immigration = this.calculateImmigration(state);

        if (rd.nextFloat() < immigration) {
            // Rejection sampling, with 10 max attempts
            for (int attempt = 0; attempt < 10; attempt++) {
                int x = rd.nextInt(state.map.width);
                int y = rd.nextInt(state.map.height);
                if (this.canPlaceHabitation(state, x, y)) {
                    this.placeHabitation(state, x, y);
                    break;
                }
            }
        }
    }
/*Les prix de l'électricité,
la richesse économique et les niveaux de pollution influencent tous la satisfaction.
* */
    public void updateSatisfaction(GameState state) {
        this.totalSatisfaction = 0.0f;
        for (int y = 0; y < state.map.height; y++) {
            for (int x = 0; x < state.map.width; x++) {
                Habitation habitation;
                try {
                    habitation = (Habitation)state.map.get(x, y);
                } catch (ClassCastException e) {
                    continue;
                }
                habitation.updateSatisfaction();
                this.totalSatisfaction += habitation.satisfaction;
            }
        }
    }

    private float calculateImmigration(GameState state) {
        this.totalPopulation = 0;
        for (int y = 0; y < state.map.height; y++) {
            for (int x = 0; x < state.map.width; x++) {
                Habitation habitation;
                try {
                    habitation = (Habitation)state.map.get(x, y);
                } catch (ClassCastException e) {
                    continue;
                }

                this.totalPopulation += habitation.population;
            }
        }

        float res = 1.0f;

        if (this.totalPopulation > 10) res = (this.totalSatisfaction / (float)this.totalPopulation - 0.5f) * 2.0f;

        // Inverse gamma, for diminishing gains as the average satisfaction reaches 1.0
        return 1.0f - (float)Math.pow(1.0f - res, 3.0f);
    }


    /*Si la zone est vide, vous pouvez construire un nouveau bâtiment,
    si elle n'est pas vide, vous ne pouvez pas...*/
    private boolean canPlaceHabitation(GameState state, int x, int y){
        Tile tile = state.map.get(x, y);
        if (tile == null) return false;

        return tile.getClass().equals(Tile.class) && tile.terrainType == TerrainType.PLAINS;
    }

    /*Mon intention initiale était que l'utilisateur puisse sélectionner l'habitation
    qu'il voulait mettre en place via un bouton, ici pour le lier à l'image*/
    private void placeHabitation(GameState state, int x, int y) {
        Tile res;
        switch (rd.nextInt(5)) {
            case 0:
                res = new SimpleHousing(state.map.get(x, y));
                break;
            case 1:
                res = new Restaurant(state.map.get(x, y));
                break;
            case 2:
                res = new TownHall(state.map.get(x, y));
                break;
            case 3:
                res = new Hospital(state.map.get(x, y));
                break;
            default:
                res = new School(state.map.get(x, y));
                break;
        }
        state.map.placeBuilding(x, y, res);
    }

    public void updatePollution(GameState state) {
        // TODO
    }

    // TODO: updatePollution methods

    public void updateGeneration(GameState state) {
        // ResourceGenerator step
        for (int y = 0; y < state.map.height; y++) {
            for (int x = 0; x < state.map.width; x++) {
                if (state.map.get(x, y) instanceof ResourceGenerator) {
                    state.map.get(x, y).updateGeneration(state);
                    state.map.get(x, y).storage.transferAll(state.globalStorage);
                }
            }
        }

        // PowerGenerator step
        for (int y = 0; y < state.map.height; y++) {
            for (int x = 0; x < state.map.width; x++) {
                if (state.map.get(x, y) instanceof PowerGenerator) {
                    for (ResourceType type : ResourceType.values()) {
                        if (type == ResourceType.ENERGY) continue;
                        state.globalStorage.transfer(type, state.map.get(x, y).storage);
                    }
                    state.map.get(x, y).updateGeneration(state);
                    state.map.get(x, y).storage.transfer(ResourceType.ENERGY, state.globalStorage);
                }
            }
        }
    }

    // TODO: updateGeneration methods
}
