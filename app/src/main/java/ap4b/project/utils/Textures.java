package ap4b.project;

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.HashSet;

// Singleton
public class Textures {
    private static Textures singleton = new Textures();

    public HashMap<String, Image> images = new HashMap();
    public HashSet<String> ctm = new HashSet();

    private Textures() {
        this.images.put("grass", new Image("/grass.png"));
        this.images.put("forest", new Image("/forest.png"));
        this.ctm.add("forest");
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
}
