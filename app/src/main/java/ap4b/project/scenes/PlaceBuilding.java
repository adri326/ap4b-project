package ap4b.project;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

class PlaceBuilding extends Button implements ClickListener {
    private MainScene scene;
    public final int price;
    private VirtualConstructor makeBuilding;

    PlaceBuilding(MainScene scene, VirtualConstructor makeBuilding, String name, int price) {
        super(name + " (" + price + "$)");
        this.price = price;
        this.scene = scene;
        this.makeBuilding = makeBuilding;
        this.setOnAction(this::onAction);
    }

    public void onAction(ActionEvent event) {
        this.scene.clickListener = this;
    }

    public void onClick(int x, int y) {
        this.scene.clickListener = null;
        Map map = this.scene.gameState.map;
        map.tiles[y][x] = this.makeBuilding.fromTile(map.tiles[y][x]);
        this.scene.scheduleDraw();
    }
}
