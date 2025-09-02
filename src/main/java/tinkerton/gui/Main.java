package tinkerton.gui;

import java.io.IOException;
import tinkerton.core.Tinkerton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Tinkerton using FXML.
 */
public class Main extends Application {

    private Tinkerton tinkerton = new Tinkerton("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setTinkerton(tinkerton);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
