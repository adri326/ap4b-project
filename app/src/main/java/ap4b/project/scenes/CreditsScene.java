package ap4b.project;

import javafx.scene.Scene;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class CreditsScene extends Scene {
    public static class CreditsPane extends VBox {
        private Button backButton = new Button("Back");

        CreditsPane() {
            super();

            this.getChildren().addAll(
                new Text("AP4B Project, created by:"),
                new Text("Osée Tchappi"),
                new Text("Adrien Burgun"),
                new Text("Tarabai Gambara"),
                new Text("Jiang YiWen"),
                backButton
            );

            this.setAlignment(Pos.CENTER);
        }
    }

    public final CreditsPane rootPane;
    public App parentApp;

    CreditsScene(App parentApp, double width, double height) {
        super(new CreditsPane(), width, height);

        this.parentApp = parentApp;
        this.rootPane = (CreditsPane)this.getRoot();

        this.rootPane.backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                parentApp.setWelcomeScene();
            }
        });
    }
}
