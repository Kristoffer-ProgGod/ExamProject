package Application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartPageController implements Initializable {

    @FXML
    Button singleUserButton;

    @FXML
    Button multiUserButton;

    @FXML
    AnchorPane startPane;



    public void switchToHomepage(javafx.event.ActionEvent event) throws IOException {
        Stage stage = null;
        Parent myNewScene = null;

        if(event.getSource() == singleUserButton){
            stage = (Stage) singleUserButton.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("../User Interface/HomePage.fxml"));
        }else if(event.getSource() == multiUserButton){
            stage = (Stage) multiUserButton.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("../User Interface/HomePage.fxml"));
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setX(100);
        stage.setY(0);
        stage.setTitle("Homepage");
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
