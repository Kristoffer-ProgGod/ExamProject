package Application;

import Domain.Note;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NotebankController {

    @FXML
    ListView<Note> noteList;

    @FXML
    Button saveButton;
    @FXML
    Button exportButton;
    @FXML
    Button addNote;
    @FXML
    Button returnButton;

    @FXML
    TextArea noteArea;
    @FXML
    TextField referenceField;

    public void returnToHomepage(ActionEvent event) throws IOException {
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


}
