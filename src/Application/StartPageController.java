package Application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;

public class StartPageController {

    @FXML
    Button singleUserButton;

    @FXML
    Button multiUserButton;

    @FXML
    AnchorPane startPane;





    @FXML
    public void loadHomepage() throws IOException {

        AnchorPane homepage = FXMLLoader.load(getClass().getResource("../User Interface/Homepage.fxml"));
        startPane.getChildren().setAll(homepage);

    }



}
