package ap4b.project;

import javafx.scene.Scene;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.geometry.*;
import javafx.beans.value.*;
import java.util.HashMap;
import java.util.HashSet;

public class MainScene extends Scene {
    public static final int MENU_HEIGHT = 50;

    public ClickListener clickListener = null;

    private Canvas canvas;
    public final VBox rootPane;
    public App parentApp;
    private GraphicsContext ctx;
    private double width;
    private double height;

    private double zoomFactor = 4.0;
    private final static double tileSize = 16.0;

    private double cx = 0.0;
    private double cy = 0.0;

    public final GameState gameState;

    private double[] getVisualPosition(double x, double y) {
        double res[] = new double[2];

        res[0] = (x - 0.5 - (double)this.gameState.map.width / 2.0) * tileSize * zoomFactor;
        res[0] += this.cx + this.width / 2.0;

        res[1] = (y - 0.5 - (double)this.gameState.map.height / 2.0) * tileSize * zoomFactor;
        res[1] += this.cy + this.height / 2.0;

        return res;
    }

    private int[] fromVisualPosition(double x, double y) {
        int res[] = new int[2];

        double x2 = x;
        x2 -= this.cx + this.width / 2.0;
        x2 /= tileSize * zoomFactor;
        x2 += 0.5 + (double)this.gameState.map.width / 2.0;
        res[0] = (int)x2;

        double y2 = y;
        y2 -= this.cy + this.height / 2.0;
        y2 /= tileSize * zoomFactor;
        y2 += 0.5 + (double)this.gameState.map.height / 2.0;
        res[1] = (int)y2;

        return res;
    }

    private boolean cullX(double vx, double vx2) {
        return vx <= this.width || vx2 >= 0;
    }

    private boolean cullY(double vy, double vy2) {
        return vy <= this.height || vy2 >= 0;
    }

    private boolean cull(double vx, double vy, double vx2, double vy2) {
        return cullX(vx, vx2) && cullY(vy, vy2);
    }

    private boolean cull(double[] visualPosition) {
        return this.cull(
            visualPosition[0],
            visualPosition[1],
            visualPosition[0] + tileSize * zoomFactor,
            visualPosition[1] + tileSize * zoomFactor
        );
    }

    /**
        Draws a single tile at (x, y).
        If the tile has a ctm texture, draws the middlemost quadrant of the texture.
    **/
    private void drawTile(int x, int y, String tile) {
        Image image = Textures.getImage(tile);
        if (image == null) return;

        double visualPosition[] = this.getVisualPosition(x, y);
        if (!cull(visualPosition)) return;
        double vx = visualPosition[0], vy = visualPosition[1];

        if (Textures.isCtm(tile)) {
            ctx.drawImage(
                image,
                tileSize, tileSize, tileSize, tileSize,
                vx,
                vy,
                tileSize * zoomFactor,
                tileSize * zoomFactor
            );
        } else {
            ctx.drawImage(
                image,
                vx,
                vy,
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
    private void drawBackgroundCTM(int x, int y, Map map) {
        // TODO: use a bool[][] map or a lambda?
        String currentTexture = map.get(x, y).getBackgroundTexture();

        if (!Textures.isCtm(currentTexture)) return;


        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                if (x + dx < 0 | x + dx >= map.width || y + dy < 0 || y + dy >= map.height) continue;

                if (!Textures.shouldDrawCtm(
                    currentTexture,
                    map.get(x + dx, y + dy).getBackgroundTexture()
                )) {
                    continue;
                }

                if (dx != 0 && dy != 0) {
                    if (
                        !Textures.shouldDrawCtm(currentTexture, map.get(x + dx, y).getBackgroundTexture())
                        || !Textures.shouldDrawCtm(currentTexture, map.get(x, y + dy).getBackgroundTexture())
                    ) continue;
                }

                double visualPosition[] = this.getVisualPosition(x + dx, y + dy);
                if (!cull(visualPosition)) continue;
                double vx = visualPosition[0], vy = visualPosition[1];

                ctx.drawImage(
                    Textures.getImage(currentTexture),
                    (dx + 1) * tileSize,
                    (dy + 1) * tileSize,
                    tileSize,
                    tileSize,
                    vx,
                    vy,
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
            double visualPosition[] = this.getVisualPosition(y, 0);
            if (!this.cullY(visualPosition[1], visualPosition[1] + this.tileSize * this.zoomFactor)) continue;

            for (int x = 0; x < this.gameState.map.width; x++) {
                drawTile(x, y, this.gameState.map.get(x, y).getBackgroundTexture());
            }
        }

        // Phase 2: draw the different CTMs
        for (int priority = 1; priority < Textures.MAX_PRIORITY; priority++) {
            for (int y = 0; y < this.gameState.map.height; y++) {
                double visualPosition[] = this.getVisualPosition(y, 0);
                if (!this.cullY(visualPosition[1], visualPosition[1] + this.tileSize * this.zoomFactor)) continue;

                for (int x = 0; x < this.gameState.map.width; x++) {
                    Integer p = Textures.getInstance().ctmPriority.get(this.gameState.map.get(x, y).getBackgroundTexture());
                    if (p != null && (int)p == priority) {
                        drawBackgroundCTM(x, y, this.gameState.map);
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
        this.canvas.setHeight(height - MENU_HEIGHT);

        this.draw();
    }

    private double mouseX = 0.0, mouseY = 0.0;
    private boolean mouseDown = false;

    private void onMouseDown(MouseEvent event) {
        this.mouseX = event.getX();
        this.mouseY = event.getY();
        this.mouseDown = true;

        if (this.clickListener != null) {
            int pos[] = this.fromVisualPosition(this.mouseX, this.mouseY);
            if (
                pos[0] >= 0 && pos[0] < this.gameState.map.width
                && pos[1] >= 0 && pos[1] < this.gameState.map.height
            ) {
                this.clickListener.onClick(pos[0], pos[1]);
            }
        }
    }

    private void onMouseUp(MouseEvent event) {
        this.mouseX = event.getX();
        this.mouseY = event.getY();
        this.mouseDown = false;
    }

    private void onMouseMoved(MouseEvent event) {
        if (this.mouseDown) {
            this.cx += event.getX() - this.mouseX;
            this.cy += event.getY() - this.mouseY;
            this.draw();
        }
        this.mouseX = event.getX();
        this.mouseY = event.getY();
    }

    private float zoomPowerFactor = 2.0f;

    private void onScroll(ScrollEvent event) {
        switch(event.getTextDeltaYUnits()) {
            case LINES:
                this.zoomPowerFactor += event.getTextDeltaY() > 0 ? 0.5 : -0.5;
                break;
            case PAGES:
                this.zoomPowerFactor += event.getTextDeltaY() / 2.0;
                break;
            case NONE:
                this.zoomPowerFactor += event.getDeltaY() * 0.02;
                break;
        }
        if (this.zoomPowerFactor < 0.0f) this.zoomPowerFactor = 0.0f;
        if (this.zoomPowerFactor > 4.0f) this.zoomPowerFactor = 4.0f;

        double oldFactor = this.zoomFactor;

        if (this.zoomPowerFactor == 0.0f) {
            this.zoomFactor = 1.0;
        } else if (this.zoomPowerFactor <= 0.75f) {
            this.zoomFactor = 1.5;
        } else if (this.zoomPowerFactor < 1.5f) {
            this.zoomFactor = 2.0;
        } else if (this.zoomPowerFactor < 2.0f) {
            this.zoomFactor = 3.0;
        } else {
            this.zoomFactor = Math.round(Math.pow(2.0, this.zoomPowerFactor));
        }

        if (this.zoomFactor != oldFactor) {
            this.cx *= this.zoomFactor / oldFactor;
            this.cy *= this.zoomFactor / oldFactor;
            draw();
        }
    }

    public void scheduleDraw() {
        this.draw();
    }

    MainScene(App parentApp, double width, double height) {
        super(new VBox(), width, height);
        this.rootPane = (VBox)this.getRoot();

        this.parentApp = parentApp;
        this.width = width;
        this.height = height;

        this.canvas = new Canvas((int)width, (int)height - MENU_HEIGHT);
        this.rootPane.setVgrow(this.canvas, Priority.ALWAYS);
        this.rootPane.getChildren().addAll(canvas);
        // this.rootPane.setAlignment(canvas, Pos.TOP_CENTER);

        this.ctx = this.canvas.getGraphicsContext2D();
        // Only available for JavaFX >= 12
        this.ctx.setImageSmoothing(false);

        this.gameState = parentApp.gameState;

        ChangeListener<Number> resizeListener = (observable, oldValue, newValue) -> {
            this.resize(this.rootPane.getWidth(), this.rootPane.getHeight());
        };
        this.rootPane.widthProperty().addListener(resizeListener);
        this.rootPane.heightProperty().addListener(resizeListener);

        this.canvas.setOnMouseMoved(this::onMouseMoved);
        this.canvas.setOnMouseDragged(this::onMouseMoved);
        this.canvas.setOnMousePressed(this::onMouseDown);
        this.canvas.setOnMouseReleased(this::onMouseUp);
        this.canvas.setOnMouseExited(this::onMouseUp);
        this.canvas.setOnScroll(this::onScroll);

        // Create a menu to add the different buildings
        GridPane menuPane = new GridPane();
        PlaceBuilding placeCoalMine = new PlaceBuilding(this, (tile) -> new CoalMine(tile), "Coal Mine", 100);
        PlaceBuilding placeUraniumMine = new PlaceBuilding(this, (tile) -> new UraniumMine(tile), "Uranium Mine", 1000);
        PlaceBuilding placeThoriumMine = new PlaceBuilding(this, (tile) -> new ThoriumMine(tile), "Thorium Mine", 1250);
        PlaceBuilding placeHospital = new PlaceBuilding(this, (tile) -> new Hospital(tile), "Hospital", 0);
        PlaceBuilding placeRestaurant = new PlaceBuilding(this, (tile) -> new Restaurant(tile), "Restaurant", 0);
        PlaceBuilding placeSimpleHousing = new PlaceBuilding(this, (tile) -> new SimpleHousing(tile), "Simple Housing", 0);
        PlaceBuilding placeSchool = new PlaceBuilding(this, (tile) -> new School(tile), "School", 0);
        menuPane.getChildren().addAll(
            placeCoalMine,
            placeUraniumMine,
            placeThoriumMine,
            placeHospital,
            placeRestaurant,
            placeSimpleHousing,
            placeSchool
        );
        menuPane.setRowIndex(placeCoalMine, 0);
        menuPane.setColumnIndex(placeCoalMine, 0);

        menuPane.setRowIndex(placeUraniumMine, 0);
        menuPane.setColumnIndex(placeUraniumMine, 1);

        menuPane.setRowIndex(placeThoriumMine, 0);
        menuPane.setColumnIndex(placeThoriumMine, 2);

        menuPane.setRowIndex(placeHospital, 0);
        menuPane.setColumnIndex(placeHospital, 3);

        menuPane.setRowIndex(placeRestaurant, 0);
        menuPane.setColumnIndex(placeRestaurant, 4);

        menuPane.setRowIndex(placeSimpleHousing, 0);
        menuPane.setColumnIndex(placeSimpleHousing, 5);

        menuPane.setRowIndex(placeSchool, 0);
        menuPane.setColumnIndex(placeSchool, 6);

        Button updateButton = new Button("Update");
        updateButton.setOnAction((event) -> {
            this.gameState.update();
            this.scheduleDraw();
        });
        menuPane.getChildren().addAll(updateButton);
        menuPane.setRowIndex(updateButton, 1);
        menuPane.setColumnIndex(updateButton, 0);

        this.rootPane.getChildren().addAll(menuPane);

        this.draw();
    }
}
