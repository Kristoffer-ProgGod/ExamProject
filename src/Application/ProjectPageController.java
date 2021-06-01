package Application;

import Domain.Note;
import Domain.Notebank;
import Domain.Project;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ProjectPageController implements Initializable {


    LinkedList<Note> tempLinkedList = new LinkedList<>();
    int maxNoteId = -1;
    double posX = 0;

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
    Button createNote;
    @FXML
    Button deleteNoteButton;
    @FXML
    Button editNoteButton;

    @FXML
    Pane addNotePane;

    @FXML
    AnchorPane root;

    @FXML
    TextArea noteArea;
    @FXML
    TextField referenceField;

    @FXML
    Pane timeline;

    @FXML
    ListView<Notebank> notebankList;
    @FXML
    ListView<Note> noteListView;

    //Create a new note
    public void createNote(ActionEvent event) {
        String text, references;
        if (event.getSource() == createNote) {
            text = noteArea.getText();
            references = referenceField.getText();

            noteArea.clear();
            referenceField.clear();

            Note note = new Note(text, references);
            note.setNoteId(maxNoteId + 1);

            tempLinkedList.add(note);
            noteListView.setItems(FXCollections.observableArrayList(tempLinkedList));
        }
    }


    //Returns the user to the Home Page
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
        initTimeline(SingletonMediator.getInstance().getCurrentProject());

    }

    public void openNotePane(ActionEvent event) {
        addNotePane.setVisible(true);

    }

    public void closePane(ActionEvent event) {
        addNotePane.setVisible(false);
    }


    public void putNoteOnTimeline(MouseEvent event) {

        Note chosenNote = noteListView.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2) {
            editNoteButton = new Button();
            deleteNoteButton = new Button();
            Pane notePane = new Pane();
            TextArea noteTextArea = new TextArea();

            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(5.0);
            dropShadow.setOffsetX(3.0);
            dropShadow.setOffsetY(3.0);
            dropShadow.setColor(Color.color(0, 0, 0));


            editNoteButton.setText("Edit");
            editNoteButton.setPrefSize(70, 30);
            editNoteButton.setLayoutX(230);
            editNoteButton.setLayoutY(140);
            editNoteButton.setOnAction(editTextArea);
            editNoteButton.setStyle("-fx-font-weight: bolder");

            deleteNoteButton.setText("Delete");
            deleteNoteButton.setPrefSize(70, 30);
            deleteNoteButton.setLayoutX(230);
            deleteNoteButton.setLayoutY(170);
            deleteNoteButton.setStyle("-fx-font-weight: bolder");
//            deleteNoteButton.setOnAction(removeNote);


            noteTextArea.setStyle("-fx-background-color: white");
            noteTextArea.setPrefSize(300, 200);
            noteTextArea.setEffect(dropShadow);

            notePane.setPrefSize(300, 230);

            notePane.setLayoutX(posX);
            posX = posX + 320;
            notePane.getChildren().addAll(noteTextArea, editNoteButton, deleteNoteButton);

            timeline.getChildren().add(notePane);
            noteTextArea.setText(chosenNote.getText());
            noteTextArea.setPrefSize(300, 200);
            noteTextArea.setEditable(false);
            noteTextArea.setEffect(dropShadow);

            SingletonMediator.getInstance().getCurrentProject().getTimeline().add(chosenNote);
        }
    }

    public void saveProject(ActionEvent event){

    }

    public void initTimeline(Project project){
        {
            for (Note note: project.getTimeline()) {

            editNoteButton = new Button();
            deleteNoteButton = new Button();
            Pane notePane = new Pane();
            TextArea noteTextArea = new TextArea();

            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(5.0);
            dropShadow.setOffsetX(3.0);
            dropShadow.setOffsetY(3.0);
            dropShadow.setColor(Color.color(0, 0, 0));


            editNoteButton.setText("Edit");
            editNoteButton.setPrefSize(70, 30);
            editNoteButton.setLayoutX(230);
            editNoteButton.setLayoutY(140);
            editNoteButton.setOnAction(editTextArea);
            editNoteButton.setStyle("-fx-font-weight: bolder");

            deleteNoteButton.setText("Delete");
            deleteNoteButton.setPrefSize(70, 30);
            deleteNoteButton.setLayoutX(230);
            deleteNoteButton.setLayoutY(170);
            deleteNoteButton.setStyle("-fx-font-weight: bolder");
//            deleteNoteButton.setOnAction(removeNote);


            noteTextArea.setStyle("-fx-background-color: white");
            noteTextArea.setPrefSize(300, 200);
            noteTextArea.setEffect(dropShadow);

            notePane.setPrefSize(300, 230);

            notePane.setLayoutX(posX);
            posX = posX + 320;
            notePane.getChildren().addAll(noteTextArea, editNoteButton, deleteNoteButton);

            timeline.getChildren().add(notePane);
            noteTextArea.setText(note.getText());
            noteTextArea.setPrefSize(300, 200);
            noteTextArea.setEditable(false);
            noteTextArea.setEffect(dropShadow);
            }
        }
    }

    EventHandler<ActionEvent> editTextArea = event -> {
        Button button = (Button) event.getSource();
        Pane pane = (Pane) button.getParent();
        TextArea textArea = (TextArea) pane.getChildren().get(0);
        if (!textArea.isEditable()) {
            textArea.setEditable(true);
            button.setText("Save");
        } else {
            textArea.setEditable(false);
            button.setText("Edit");
        }
    };

//    //wtf it does not work. Why not?!?!?!?
//    //HJÆÆÆÆÆÆÆÆÆLP!!
//    // Does not know which Note object is selected only which notePane,
//    // does not remove 320 from posX if more than one is removed at a time.
//    EventHandler<ActionEvent> removeNote = event -> {
//
//        Button button = (Button) event.getSource();
//        Pane notePane = (Pane) button.getParent();
//        Pane timeline = (Pane) notePane.getParent();
//
//        timeline.getChildren().remove(notePane);
//
//
//        timelineNoteLinkedList.remove();
//        System.out.println(timelineNoteLinkedList.getFirst());
//        posX = posX - 320;
//        if(timelineNoteLinkedList.isEmpty()){
//            posX = 0;
//        }
//
//    };
//
//    public void repositionNotes() {
//
//    }
//
//
//    public void dragNote(MouseDragEvent event) {
//        Label note = (Label) event.getSource();
//
//        timeline.setOnDragDetected(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                Dragboard db = note.startDragAndDrop(TransferMode.MOVE);
//                event.consume();
//            }
//        });
//
//
//        timeline.setOnDragOver(new EventHandler<DragEvent>() {
//            @Override
//            public void handle(DragEvent event) {
//                if (event.getSource() != timeline) {
//                    event.acceptTransferModes(TransferMode.MOVE);
//                }
//                event.consume();
//            }
//        });
//
//        timeline.setOnDragDropped(new EventHandler<DragEvent>() {
//            @Override
//            public void handle(DragEvent event) {
//                event.setDropCompleted(true);
//                event.consume();
//            }
//        });
//
//    }

}


