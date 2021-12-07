package ap4b.project;

import javafx.scene.Scene;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.geometry.*;

// Note: we're overusing inheritance because we're told to
public class WelcomeScene extends Scene {
    public static class WelcomePane extends GridPane {
        private Button startButton = new Button("Start");
        private Button creditsButton = new Button("Credits");

        WelcomePane() {
            super();
            this.setAlignment(Pos.CENTER);

            VBox titleBox = new VBox(10);

            Text welcomeText = new Text("Welcome to our AP4B project:");
            welcomeText.setFont(Font.font("sans-serif", FontWeight.NORMAL, 16));

            Text titleText = new Text("Sim Power");
            titleText.setFont(Font.font("sans-serif", FontWeight.NORMAL, 24));

            titleBox.getChildren().addAll(welcomeText, titleText);
            titleBox.setAlignment(Pos.CENTER);

            this.add(titleBox, 1, 0);
            this.setColumnSpan(titleBox, 2);

            VBox startBox = new VBox(0);
            startBox.getChildren().addAll(startButton);
            startBox.setAlignment(Pos.CENTER);
            this.add(startBox, 1, 1);

            VBox creditsBox = new VBox(0);
            creditsBox.getChildren().addAll(creditsButton);
            creditsBox.setAlignment(Pos.CENTER);
            this.add(creditsBox, 2, 1);

            ColumnConstraints columns[] = new ColumnConstraints[]{
                new ColumnConstraints(),
                new ColumnConstraints(),
                new ColumnConstraints(),
                new ColumnConstraints(),
            };

            columns[0].setPercentWidth(20);
            columns[1].setPercentWidth(30);
            columns[2].setPercentWidth(30);
            columns[3].setPercentWidth(20);

            this.getColumnConstraints().addAll(columns);
        }
    }

    public final WelcomePane rootPane;
    public App parentApp;

    WelcomeScene(App parentApp, double width, double height) {
        super(new WelcomePane(), width, height);

        this.parentApp = parentApp;
        this.rootPane = (WelcomePane)this.getRoot();

        this.rootPane.startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Start");
            }
        });

        this.rootPane.creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                parentApp.setCreditsScene();
            }
        });
    }
}
