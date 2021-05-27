package Application;

import Domain.Notebank;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProjectPageController implements Initializable {

    @FXML
    Button returnButton;
    @FXML
    Button saveButton;
    @FXML
    Button exportButton;

    @FXML
    ScrollPane timeline;

    @FXML
    ListView notebankList;
    @FXML
    ListView noteList;





    public void returnToProjectPage(ActionEvent event) throws IOException {
        Stage stage = null;
        Parent myNewScene = null;

        if (event.getSource() == returnButton) {
            stage = (Stage) returnButton.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("../User Interface/HomePage.fxml"));
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Homepage");
        stage.show();
    }


        @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}


