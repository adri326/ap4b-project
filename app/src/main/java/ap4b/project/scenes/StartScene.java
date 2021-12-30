package ap4b.project;

import javafx.scene.Scene;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class StartScene extends Scene {
    public static class StartPane extends VBox {
        private IntegerField widthField = new IntegerField(10, 100, 50);
        private IntegerField heightField = new IntegerField(10, 100, 50);
        private IntegerField difficultyField = new IntegerField(1, 10, 5);
        private Button continueButton = new Button("Create world!");
        private Button backButton = new Button("Back");

        StartPane() {
            super(10);
            this.setAlignment(Pos.CENTER);

            HBox widthBox = new HBox(10);
            widthBox.setAlignment(Pos.CENTER);
            widthBox.getChildren().addAll(new Label("Width:"), this.widthField);

            HBox heightBox = new HBox(10);
            heightBox.setAlignment(Pos.CENTER);
            heightBox.getChildren().addAll(new Label("Height:"), this.heightField);

            HBox difficultyBox = new HBox(10);
            difficultyBox.setAlignment(Pos.CENTER);
            difficultyBox.getChildren().addAll(new Label("Difficulty [1-10]:"), this.difficultyField);

            HBox buttonsBox = new HBox(20);
            buttonsBox.setAlignment(Pos.CENTER);
            buttonsBox.setPadding(new Insets(10, 10, 10, 10));
            buttonsBox.getChildren().addAll(this.backButton, this.continueButton);

            this.getChildren().addAll(widthBox, heightBox, difficultyBox, buttonsBox);
        }
    }

    public final StartPane rootPane;
    public App parentApp;

    StartScene(App parentApp, double width, double height) {
        super(new StartPane(), width, height);

        this.parentApp = parentApp;
        this.rootPane = (StartPane)this.getRoot();
        StartPane rootPane = this.rootPane;

        this.rootPane.backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                parentApp.setWelcomeScene();
            }
        });

        this.rootPane.continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(rootPane.widthField.getValue());
                System.out.println(rootPane.heightField.getValue());
                System.out.println(rootPane.difficultyField.getValue());
                parentApp.gameState = new GameState(
                    rootPane.widthField.getValue(),
                    rootPane.heightField.getValue(),
                    rootPane.difficultyField.getValue()
                );
                parentApp.setMainScene();
            }
        });
    }
}
