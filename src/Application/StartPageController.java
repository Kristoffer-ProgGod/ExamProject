package Application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartPageController implements Initializable {

    public static ProjectStrategy singleProjectStrategy = new SingleUser();
    public static ProjectStrategy multiProjectStrategy = new MultiUser();
    public static NotebankStrategy singleNotebankStrategy = new SingleUser();
    public static NotebankStrategy multiNotebankStrategy = new MultiUser();

    @FXML
    Button singleUserButton;

    @FXML
    Button multiUserButton;

    @FXML
    AnchorPane startPane;


/*
Clears the page and loads the Homepage.fxml for both the Single User and Multi User Strategy depending on Chosen Button
 */
    public void switchToHomepage(javafx.event.ActionEvent event) throws IOException {
        Stage stage = null;
        Parent myNewScene = null;

        if(event.getSource() == singleUserButton){
            SingletonMediator.getInstance().setCurrentProjectStrategy(singleProjectStrategy);
            SingletonMediator.getInstance().setCurrentNotebankStrategy(singleNotebankStrategy);
            stage = (Stage) singleUserButton.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("../User Interface/HomePage.fxml"));
        }else if(event.getSource() == multiUserButton){
            SingletonMediator.getInstance().setCurrentProjectStrategy(multiProjectStrategy);
            SingletonMediator.getInstance().setCurrentNotebankStrategy(multiNotebankStrategy);
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
