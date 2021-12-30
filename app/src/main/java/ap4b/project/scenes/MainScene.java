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

    public final GameState gameState;

    /**
        Draws a single tile at (x, y).
        If the tile has a ctm texture, draws the middlemost quadrant of the texture.
    **/
    private void drawTile(int x, int y, String tile) {
        Image image = Textures.getImage(tile);
        if (image == null) return;

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
    private void drawTileCTM(int x, int y, Map map) {
        // TODO: use a bool[][] map or a lambda?
        String currentTexture = map.get(x, y).terrainType.getTexture();

        if (!Textures.isCtm(currentTexture)) return;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                if (x + dx < 0 | x + dx >= map.width || y + dy < 0 || y + dy >= map.height) continue;

                if (!Textures.shouldDrawCtm(
                    currentTexture,
                    map.get(x + dx, y + dy).terrainType.getTexture()
                )) {
                    continue;
                }

                if (dx != 0 && dy != 0) {
                    if (
                        !Textures.shouldDrawCtm(currentTexture, map.get(x + dx, y).terrainType.getTexture())
                        || !Textures.shouldDrawCtm(currentTexture, map.get(x, y + dy).terrainType.getTexture())
                    ) continue;
                }

                ctx.drawImage(
                    Textures.getImage(currentTexture),
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

        // Phase 1: draw the background (full tiles)
        for (int y = 0; y < this.gameState.map.height; y++) {
            for (int x = 0; x < this.gameState.map.width; x++) {
                drawTile(x, y, this.gameState.map.get(x, y).terrainType.getTexture());
            }
        }

        // Phase 2: draw the different CTMs
        for (int priority = 1; priority < Textures.MAX_PRIORITY; priority++) {
            for (int y = 0; y < this.gameState.map.height; y++) {
                for (int x = 0; x < this.gameState.map.width; x++) {
                    Integer p = Textures.getInstance().ctmPriority.get(this.gameState.map.get(x, y).terrainType.getTexture());
                    if (p != null && (int)p == priority) {
                        drawTileCTM(x, y, this.gameState.map);
                    }
                }
            }
        }

        // Phase 3 (TODO): draw the buildings themselves
        for (int y = 0; y < this.gameState.map.height; y++) {
            for (int x = 0; x < this.gameState.map.width; x++) {
                drawTile(x, y, this.gameState.map.get(x, y).getTexture());
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

        this.gameState = parentApp.gameState;

        ChangeListener<Number> resizeListener = (observable, oldValue, newValue) -> {
            this.resize(this.rootPane.getWidth(), this.rootPane.getHeight());
        };
        this.rootPane.widthProperty().addListener(resizeListener);
        this.rootPane.heightProperty().addListener(resizeListener);

        this.draw();
    }
}
