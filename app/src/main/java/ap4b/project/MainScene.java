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

    private String tmpMap[][] = new String[10][10];

    private void draw() {
        ctx.clearRect(0, 0, this.width, this.height);
        ctx.setFill(Color.BLUE);

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                // TODO: transitions between biomes
                ctx.drawImage(
                    this.images.get(this.tmpMap[x][y]),
                    x * tileSize * zoomFactor,
                    y * tileSize * zoomFactor,
                    tileSize * zoomFactor,
                    tileSize * zoomFactor
                );
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
