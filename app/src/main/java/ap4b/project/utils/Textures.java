package ap4b.project;

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.HashSet;

// Singleton
public class Textures {
    private static Textures singleton = new Textures();
    public static final int MAX_PRIORITY = 4;

    public HashMap<String, Image> images = new HashMap();
    public HashSet<String> ctm = new HashSet();
    public HashMap<String, Integer> ctmPriority = new HashMap();

    private Textures() {
        this.images.put("grass", new Image("/grass.png"));
        this.images.put("hills", new Image("/hills.png"));
        this.images.put("forest", new Image("/forest.png"));
        this.images.put("water", new Image("/water.png"));
        this.images.put("coal", new Image("/coal.png"));
        this.images.put("uranium", new Image("/uranium.png"));
        this.images.put("thorium", new Image("/thorium.png"));

        this.ctm.add("forest");
        this.ctm.add("water");
        this.ctm.add("coal");
        this.ctm.add("uranium");
        this.ctm.add("thorium");

        this.ctmPriority.put("grass", 0);
        this.ctmPriority.put("hills", 0);
        this.ctmPriority.put("coal", 1);
        this.ctmPriority.put("uranium", 1);
        this.ctmPriority.put("thorium", 1);
        this.ctmPriority.put("water", 2);
        this.ctmPriority.put("forest", 3);


        this.images.put("hospital", new Image("/hospital.png"));
        this.images.put("restaurant", new Image("/restaurant.png"));
        this.images.put("school", new Image("/school.png"));
        this.images.put("simplehousing", new Image("/simplehousing.png"));
        this.images.put("townhall", new Image("/townhall.png"));
    }

    public static Textures getInstance() {
        return Textures.singleton;
    }

    // May return null
    public static Image getImage(String name) {
        return Textures.singleton.images.get(name);
    }

    public static boolean isCtm(String name) {
        return Textures.singleton.ctm.contains(name);
    }

    public static boolean shouldDrawCtm(String current, String neighbor) {
        Integer c = Textures.singleton.ctmPriority.get(current);
        Integer n = Textures.singleton.ctmPriority.get(neighbor);
        if (c == null || n == null) return false;
        return (int)c > (int)n;
    }
}
