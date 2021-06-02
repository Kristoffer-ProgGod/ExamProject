package Application;

import Domain.Note;
import Domain.Notebank;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NotebankController implements Initializable {

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
    Label notebankTitleLabel;

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


    public void addNewNote(ActionEvent actionEvent) {
        Note note = new Note(noteArea.getText(), referenceField.getText());
        SingletonMediator.getInstance().getCurrentNotebank().getNotebankLinkedList().add(note);
        noteList.getItems().add(note);
        noteArea.clear();
        referenceField.clear();
    }

    public void saveProject(ActionEvent actionEvent) throws SQLException {
        Notebank notebank = SingletonMediator.getInstance().getCurrentNotebank();
        SingletonMediator.getInstance().getCurrentNotebankStrategy().saveNotebank(notebank);
    }

    public void generateTitle(){
        notebankTitleLabel.setText("Notebank: " + SingletonMediator.getInstance().getCurrentNotebank().getNotebankTitle());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateTitle();
        noteList.getItems().addAll(SingletonMediator.getInstance().getCurrentNotebank().getNotebankLinkedList());
    }
}
