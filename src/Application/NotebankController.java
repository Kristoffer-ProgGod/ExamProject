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
import javafx.scene.layout.Pane;
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
    Button addNote;
    @FXML
    Button returnButton;
    @FXML
    Button deleteNoteButton;
    @FXML
    Button deleteNotebankButton;
    @FXML
    Button saveNotebankNameButton;
    @FXML
    Button closeNotebankNameButton;
    @FXML
    Label notebankTitleLabel;

    @FXML
    TextArea noteArea;
    @FXML
    TextField referenceField;
    @FXML
    TextField newNotebankNameField;

    @FXML
    Pane editNotebankName;


    SingleUser singleUser = new SingleUser();
    MultiUser multiUser = new MultiUser();

    /*
    Returns the user to the homepage on button press
     */
    public void returnToHomepage(ActionEvent event) throws IOException {
        Stage stage = null;
        Parent myNewScene = null;


            stage = (Stage) returnButton.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("../User Interface/HomePage.fxml"));

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Homepage");
        stage.show();
    }


    /*
    Calls the relevant addNote method and adds a new note to the notebank listview when pressing the add button in the notebank page
     */
    public void addNewNote(ActionEvent actionEvent) {
        Note note = new Note(noteArea.getText(), referenceField.getText());
        SingletonMediator.getInstance().getCurrentNotebank().getNotebankLinkedList().add(note);
        noteList.getItems().add(note);

        noteArea.clear();
        referenceField.clear();
    }

    /*
    Saves the notebank when the Save button is pressed
     */
    public void saveProject(ActionEvent actionEvent) throws SQLException {
        Notebank notebank = SingletonMediator.getInstance().getCurrentNotebank();
        SingletonMediator.getInstance().getCurrentNotebankStrategy().saveNotebank(notebank);
    }

    /*
    Removes the selected note from the listview and the notebank linkedlist
     */
    public void deleteNote(ActionEvent actionEvent){
        noteList.getItems();
        Note chosenNote = noteList.getSelectionModel().getSelectedItem();
        noteList.getItems().remove(chosenNote);
        SingletonMediator.getInstance().getCurrentNotebank().getNotebankLinkedList().remove(chosenNote);

    }

    /*
    Sets the text in the top of the notebank page to match the name of the notebank
     */
    public void generateTitle(){
        notebankTitleLabel.setText("Notebank: " + SingletonMediator.getInstance().getCurrentNotebank().getNotebankTitle());
    }

    /*
    Calls all methods used to initialize the notebank page
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateTitle();
        editNotebankName.setVisible(false);
        noteList.getItems().addAll(SingletonMediator.getInstance().getCurrentNotebank().getNotebankLinkedList());
    }

    /*
    Deletes the current notebank from database or locally depending on user strategy
     */
    public void deleteNotebank(ActionEvent event) throws IOException, SQLException {
        SingletonMediator.getInstance().getCurrentNotebankStrategy().deleteNotebank();
        returnToHomepage(event);
    }

    /*
    Sets the pane used to edit the notebank name to visible
     */
    public void openNotebankName(ActionEvent event) throws IOException, SQLException {
        editNotebankName.setVisible(true);
    }

    /*
    Changes the notebank name to the text entered in the editNotebankName pane
     */
    public void saveNewNotebankName(ActionEvent event) throws IOException, SQLException {
        singleUser.editNotebankTitle(newNotebankNameField.getText());
        SingletonMediator.getInstance().getCurrentNotebankStrategy().saveNotebank(SingletonMediator.getInstance().getCurrentNotebank());

        editNotebankName.setVisible(false);
        generateTitle();
    }

    /*
    Hides the editNotebankName pane once the save button or the close button is pressed
     */
    public void hideNotebankNamePane(ActionEvent event) {
        editNotebankName.setVisible(false);
        newNotebankNameField.clear();
    }
}
