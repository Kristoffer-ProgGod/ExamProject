package Application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;

public class SceneSwitcher {

    public void switchScene(File scene) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(scene.getPath()));


    }
}
