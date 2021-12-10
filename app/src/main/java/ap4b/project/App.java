package ap4b.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private double width = 600;
    private double height = 600;
    private Stage stage = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        this.stage.setTitle("AP4B Project");

        this.setWelcomeScene();
        this.stage.show();
    }

    public void setWelcomeScene() {
        this.stage.setScene(new WelcomeScene(this, this.width, this.height));
    }

    public void setCreditsScene() {
        this.stage.setScene(new CreditsScene(this, this.width, this.height));
    }

    public void setStartScene() {
        this.stage.setScene(new StartScene(this, this.width, this.height));
    }

    public void setMainScene() {
        this.stage.setScene(new MainScene(this, this.width, this.height));
    }
}
