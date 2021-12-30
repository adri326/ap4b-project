package ap4b.project;

import javafx.scene.Scene;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.image.Image;
import javafx.geometry.*;
import javafx.beans.value.*;
import java.util.HashMap;
import java.util.HashSet;

public class MainScene extends Scene {
    private Canvas canvas;
    public final StackPane rootPane;
    public App parentApp;
    private GraphicsContext ctx;
    private double width;
    private double height;
    private double zoomFactor = 4.0;
    private final static int tileSize = 16;

    private HashMap<String, Image> images = new HashMap();
    private HashSet<String> is_ctm = new HashSet();

    private String tmpMap[][] = new String[10][10];

    private void drawTile(int x, int y, String tile) {
        Image image = this.images.get(tile);

        if (is_ctm.contains(tile)) {
            ctx.drawImage(
                image,
                tileSize,
                tileSize,
                tileSize,
                tileSize,
                x * tileSize * zoomFactor,
                y * tileSize * zoomFactor,
                tileSize * zoomFactor,
                tileSize * zoomFactor
            );
        } else {
            ctx.drawImage(
                image,
                x * tileSize * zoomFactor,
                y * tileSize * zoomFactor,
                tileSize * zoomFactor,
                tileSize * zoomFactor
            );
        }
    }

    // This is awfully inefficient, but we weren't given enough time
    private void drawTileCTM(int x, int y, String[][] map) {
        int width = map.length;
        int height = map[0].length;

        if (!is_ctm.contains(map[x][y])) return;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                if (x + dx < 0 | x + dx >= width || y + dy < 0 || y + dy >= height) continue;
                if (map[x + dx][y + dy] == map[x][y]) continue;

                ctx.drawImage(
                    this.images.get(map[x][y]),
                    (dx + 1) * tileSize,
                    (dy + 1) * tileSize,
                    tileSize,
                    tileSize,
                    (x + dx) * tileSize * zoomFactor,
                    (y + dy) * tileSize * zoomFactor,
                    tileSize * zoomFactor,
                    tileSize * zoomFactor
                );
            }
        }
    }

    private void draw() {
        ctx.clearRect(0, 0, this.width, this.height);
        ctx.setFill(Color.BLUE);

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                drawTile(x, y, this.tmpMap[x][y]);
            }
        }

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                drawTileCTM(x, y, this.tmpMap);
            }
        }
    }

    private void resize(double width, double height) {
        this.width = width;
        this.height = height;

        this.canvas.setWidth(width);
        this.canvas.setHeight(height);

        this.draw();
    }

    MainScene(App parentApp, double width, double height) {
        super(new StackPane(), width, height);
        this.rootPane = (StackPane)this.getRoot();

        this.images.put("grass", new Image("/grass.png"));
        this.images.put("forest", new Image("/forest.png"));
        this.is_ctm.add("forest");

        this.parentApp = parentApp;
        this.width = width;
        this.height = height;

        this.canvas = new Canvas((int)width, (int)height);
        this.rootPane.getChildren().addAll(canvas);
        this.rootPane.setAlignment(canvas, Pos.TOP_CENTER);

        this.ctx = this.canvas.getGraphicsContext2D();
        // Only available for JavaFX >= 12
        this.ctx.setImageSmoothing(false);

        ChangeListener<Number> resizeListener = (observable, oldValue, newValue) -> {
            this.resize(this.rootPane.getWidth(), this.rootPane.getHeight());
        };
        this.rootPane.widthProperty().addListener(resizeListener);
        this.rootPane.heightProperty().addListener(resizeListener);

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                this.tmpMap[x][y] = Math.random() < 0.5 ? "grass" : "forest";
            }
        }
        this.draw();
    }
}
