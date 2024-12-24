package sample.one_time_pad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 240);
        stage.setTitle("One Time Pad Encoder");
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("/logo.png")));
        stage.setScene(scene);
        HelloController.setStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
