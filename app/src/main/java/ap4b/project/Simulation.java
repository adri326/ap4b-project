package ap4b.project;
import javafx.scene.image.Image;
import java.util.Random;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Simulation {
    public int difficulty;
    private float totalSatisfaction = 0.0f;
   private int population =0;
    public Simulation(int difficulty) {
        this.difficulty = difficulty;
    }


    public void updatePopulation( GameState state) {
        this.population = 0;
        for (int y = 0; y < state.map.height; y++) {
            for (int x = 0; x < state.map.width; x++) {
                String name = state.habitation.getTexture();
                switch (name){
                    case "hospital":
                        state.hospital.addPopulation();
                        break;
                    case "restaurant":
                        state.restaurant.addPopulation();
                        break;
                    case "school":
                        state.school.addPopulation();
                        break;
                    case "simplehousing" :
                        state.simpleHousing.addPopulation();
                        break;
                    case "townhall":
                        state.townHall.addPopulation();
                        break;
                }
            }
        }

    }
/*Les prix de l'électricité,
la richesse économique et les niveaux de pollution influencent tous la satisfaction.
* */
    public void updateSatisfaction( GameState state) {
       /* levelSatisfaction -= state.electricityPrice*2;
        levelSatisfaction += state.bank.getMoney()/100;
        levelSatisfaction -= pollution/10;*/
        state.habitation.updateSatisfaction();
    }

    private float calculateImmigration(GameState state) {
        // TODO
        int populationAvant = population;
        updatePopulation(state);

        int populationImmigration = this.population - populationAvant;
        float tauxImmigration = populationImmigration/population;
        return tauxImmigration ;


    }


/*Si la zone est vide, vous pouvez construire un nouveau bâtiment,
si elle n'est pas vide, vous ne pouvez pas...*/
    private  boolean canPlaceHabitation(GameState state, int x, int y){
        if(!state.map.placeBuilding(x,y,state.habitation)) {
            return  true;
        }
        else {
            return false;
        }

    }
    /*Mon intention initiale était que l'utilisateur puisse sélectionner l'habitation
    qu'il voulait mettre en place via un bouton, ici pour le lier à l'image*/
    private void placeHabitation(GameState state) {
        for (int i = 1;i<=10;i++) {
            Random rd1 = new Random();
            int x=rd1.nextInt(state.map.width+1);
            Random rd2 = new Random();
            int y=rd2.nextInt(state.map.height+1);
            if ( canPlaceHabitation(state, x, y) && state.tile[x][y].terrainType = ap4b.project.TerrainType.PLAINS){
                JFrame f = new JFrame("Quel bâtiment voulez-vous mettre en");

                JButton SimpleHousingButton = new JButton("SimpleHousing");
                SimpleHousingButton.setBounds(1, 1, 40, 30);
                SimpleHousingButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        HashMap<String, Image> images = new HashMap();
                        images.put("simplehousing", new Image("/simplehousing.png"));

                    }
                });
                f.add(SimpleHousingButton);
                JButton TownHallButton = new JButton("TownHall");
                TownHallButton.setBounds(80, 1, 40, 30);
                TownHallButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        HashMap<String, Image> images = new HashMap();
                        images.put("townhall", new Image("/townhall.png"));
                    }
                });
                f.add(TownHallButton);
                JButton HospitalButton = new JButton("Hospital");
                HospitalButton.setBounds(1, 40, 40, 30);
                HospitalButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        HashMap<String, Image> images = new HashMap();
                        images.put("hospital", new Image("/hospital.png"));
                    }
                });
                f.add(HospitalButton);
                JButton SchoolButton = new JButton("School");
                SchoolButton.setBounds(80, 40, 40, 30);
                SchoolButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        HashMap<String, Image> images = new HashMap();
                        images.put("school", new Image("/school.png"));

                    }
                });
                f.add(SchoolButton);
                JButton RestaurantButton = new JButton("Restaurant");
                RestaurantButton.setBounds(130, 1, 40, 30);
                RestaurantButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        HashMap<String, Image> images = new HashMap();
                        images.put("restaurant", new Image("/restaurant.png"));
                    }
                });
                f.add(RestaurantButton);
                f.setSize(400, 400);
                f.setLayout(null);
                f.setVisible(true);

            }
        else{
                System.out.println("Désolé, il y a déjà un bâtiment à cet endroit et il ne peut pas être dupliqué.");
            }
            // TODO
        }
    }

    public void updatePollution(GameState state) {
        // TODO
    }

    // TODO: updatePollution methods

    public void updateGeneration(GameState state) {

        // TODO
    }

    // TODO: updateGeneration methods
}
