package ap4b.project;

import javafx.scene.Scene;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class StartScene extends Scene {
    public static class StartPane extends GridPane {
        // I'd have used something else but I can't figure out how to put together the TextFormatter class
        // and the documentation has no example. This isn't a TODO per se, but if anyone wants
        // to paste in their code for a numerical field they can and I'll happily merge it - Shad
        private TextField widthField = new TextField("100");
        private TextField heightField = new TextField("100");
        private Button continueButton = new Button("Create world!");
        private Button backButton = new Button("Back");

        StartPane() {
            super();
            this.setAlignment(Pos.CENTER);

            HBox widthBox = new HBox(10);
            widthBox.getChildren().addAll(new Text("Width:"), this.widthField);
            this.add(widthBox, 0, 0);

            HBox heightBox = new HBox(10);
            heightBox.getChildren().addAll(new Text("Height:"), this.heightField);
            this.add(heightBox, 0, 1);

            this.add(this.continueButton, 0, 2);
            this.add(this.backButton, 0, 3);
        }
    }

    public final StartPane rootPane;
    public App parentApp;

    StartScene(App parentApp, double width, double height) {
        super(new StartPane(), width, height);

        this.parentApp = parentApp;
        this.rootPane = (StartPane)this.getRoot();

        this.rootPane.backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                parentApp.setWelcomeScene();
            }
        });
    }
}
