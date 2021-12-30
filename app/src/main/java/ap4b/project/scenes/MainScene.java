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

    // TODO: remove
    private String tmpMap[][] = new String[10][10];

    /**
        Draws a single tile at (x, y).
        If the tile has a ctm texture, draws the middlemost quadrant of the texture.
    **/
    private void drawTile(int x, int y, String tile) {
        Image image = Textures.getImage(tile);

        if (Textures.isCtm(tile)) {
            ctx.drawImage(
                image,
                tileSize, tileSize, tileSize, tileSize,
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
    /**
        Draws the connected part of a texture at (x, y), iff that texture is a ctm texture and
        the adjacent tiles aren't already occupied by it.
    **/
    private void drawTileCTM(int x, int y, String[][] map) {
        int width = map.length;
        int height = map[0].length;

        if (!Textures.isCtm(map[x][y])) return;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                if (x + dx < 0 | x + dx >= width || y + dy < 0 || y + dy >= height) continue;
                if (!Textures.shouldDrawCtm(map[x][y], map[x + dx][y + dy])) continue;
                if (dx != 0 && dy != 0) {
                    if (
                        !Textures.shouldDrawCtm(map[x][y], map[x + dx][y])
                        || !Textures.shouldDrawCtm(map[x][y], map[x][y + dy])
                    ) continue;
                }

                ctx.drawImage(
                    Textures.getImage(map[x][y]),
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

        for (int priority = 1; priority < Textures.MAX_PRIORITY; priority++) {
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    if (Textures.getInstance().ctmPriority.get(this.tmpMap[x][y]) == priority) {
                        drawTileCTM(x, y, this.tmpMap);
                    }
                }
            }
        }
    }

    // No way to avoid the horrible blinking
    /**
        Resizes the inner canvas and updates width and height.
        Called whenever the window is rescaled.
    **/
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
                this.tmpMap[x][y] = Math.random() < 0.9 ? "grass" : "hills";
            }
        }
        for (int n = 0; n < 5; n++) {
            this.tmpMap[(int)Math.floor(Math.random() * 10)][(int)Math.floor(Math.random() * 10)] = "forest";
        }
        for (int n = 0; n < 5; n++) {
            this.tmpMap[(int)Math.floor(Math.random() * 10)][(int)Math.floor(Math.random() * 10)] = "coal";
        }
        for (int n = 0; n < 5; n++) {
            this.tmpMap[(int)Math.floor(Math.random() * 10)][(int)Math.floor(Math.random() * 10)] = "uranium";
        }
        for (int n = 0; n < 5; n++) {
            this.tmpMap[(int)Math.floor(Math.random() * 10)][(int)Math.floor(Math.random() * 10)] = "thorium";
        }
        int x = 4;
        int y = 0;
        while (y < 10) {
            this.tmpMap[x][y] = "water";
            if (Math.random() < 0.6) {
                y += 1;
            } else {
                x += Math.random() < 0.5 ? 1 : -1;
            }
            if (x < 0) x = 0;
            if (x >= 10) x = 9;
        }
        this.draw();
    }
}
