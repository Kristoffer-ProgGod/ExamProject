package Application;

import Domain.Notebank;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
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
    Button addNewNote;
    @FXML
    Button cancelButton;
    @FXML
    Button addNote;

    @FXML
    Pane addNotePane;

    @FXML
    TextArea noteArea;
    @FXML
    TextField referenceField;

    @FXML
    ScrollPane timeline;

    @FXML
    ListView notebankList;
    @FXML
    ListView noteList;





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


        @Override
    public void initialize(URL location, ResourceBundle resources) {
        addNotePane.setVisible(false);

    }

    public void openNotePane(ActionEvent event) {
        addNotePane.setVisible(true);

    }

    public void closePane(ActionEvent event) {
        addNotePane.setVisible(false);
    }
}


