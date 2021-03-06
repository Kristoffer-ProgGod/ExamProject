package Application;

import Domain.Note;
import Domain.Notebank;
import Domain.Project;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ProjectPageController implements Initializable {


    int maxNoteId = -1;

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
    Button deleteProjectButton;
    @FXML
    Button editProjectButton;
    @FXML
    Button saveProjectNameButton;
    @FXML
    Button closeProjectNameButton;

    @FXML
    Pane addNotePane;
    @FXML
    Pane editTitlePane;
    @FXML
    Pane timeline;
    @FXML
    AnchorPane root;

    @FXML
    TextArea noteArea;
    @FXML
    TextField referenceField;
    @FXML
    TextField newProjectNameField;

    @FXML
    Label projectNameLabel;

    @FXML
    ListView<Notebank> notebankList;
    @FXML
    ListView<Note> noteListView;


    //Create a new note
    public void createNote(ActionEvent event) throws SQLException {
        String text, references;
        if (event.getSource() == createNote) {
            text = noteArea.getText();
            references = referenceField.getText();

            noteArea.clear();
            referenceField.clear();

            Note note = new Note(text, references);
            note.setNoteId(maxNoteId + 1);

            SingletonMediator.getInstance().getCurrentNotebank().getNotebankLinkedList().add(note);
            SingletonMediator.getInstance().getCurrentNotebankStrategy().saveNotebank(SingletonMediator.getInstance().getCurrentNotebank());
            noteListView.getItems().clear();
            noteListView.getItems().addAll(SingletonMediator.getInstance().getCurrentNotebank().getNotebankLinkedList());
            addNotePane.setVisible(false);
        }
    }


    //Returns the user to the Home Page
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addNotePane.setVisible(false);
        editTitlePane.setVisible(false);
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
            initializeNotepane(chosenNote);

            SingletonMediator.getInstance().getCurrentProject().getTimeline().add(chosenNote);
        }
    }

    public void initializeNotepane(Note note) {

        editNoteButton = new Button();
        deleteNoteButton = new Button();
        Notepane notepane = new Notepane();
        TextArea noteTextArea = new TextArea();

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0, 0, 0));

        ImageView dragIcon = new ImageView();
        dragIcon.setFitWidth(20);
        dragIcon.setFitHeight(20);



        Image icon = new Image("./User Interface/601-6017219_move-drag-icon-png-transparent-png.png");
        dragIcon.setImage(icon);


        editNoteButton.setText("Edit");
        editNoteButton.setPrefSize(50, 15);
        editNoteButton.setLayoutX(60);
        editNoteButton.setLayoutY(110);
        editNoteButton.setOnAction(editTextArea);
        editNoteButton.setStyle("-fx-font-size: 13");

        deleteNoteButton.setText("Delete");
        deleteNoteButton.setPrefSize(60, 15);
        deleteNoteButton.setLayoutX(115);
        deleteNoteButton.setLayoutY(110);
        deleteNoteButton.setStyle("-fx-font-size: 13");
        deleteNoteButton.setOnAction(removeNote);


        noteTextArea.setStyle("-fx-background-color: white");
        noteTextArea.setPrefSize(180, 135);
        noteTextArea.setEffect(dropShadow);
        noteTextArea.setLayoutY(20);


        notepane.setPrefSize(180, 155);
        notepane.setStyle("-fx-background-color: steelblue");
        notepane.setOnMouseEntered(dragNote);
        notepane.setNote(note);
        noteTextArea.setEditable(false);
        noteTextArea.setOnMouseEntered(dragNote);
        noteTextArea.setText(note.getText());

        notepane.setLayoutX(note.getxPos());
        notepane.setLayoutY(note.getyPos());
        notepane.getChildren().addAll(noteTextArea, editNoteButton, deleteNoteButton, dragIcon);

        timeline.getChildren().add(notepane);

    }


    public void saveProject(ActionEvent event) throws SQLException {
        SingletonMediator.getInstance().getCurrentProjectStrategy().saveProject(SingletonMediator.getInstance().getCurrentProject());
    }

    public void loadNotebanks() {
        try {

            for (Notebank notebank : HomepageController.notebanks) {
                notebankList.getItems().add(notebank);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void loadProjectName() {
        projectNameLabel.setText("Project Name: " + SingletonMediator.getInstance().getCurrentProject().getProjectTitle());
    }

    public void initTimeline(Project project) {
        loadProjectName();
        loadNotebanks();
        try {

            for (Note note : project.getTimeline()) {
                initializeNotepane(note);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No projects found");
        }
    }

    EventHandler<ActionEvent> editTextArea = event -> {
        Button button = (Button) event.getSource();
        Notepane notepane = (Notepane) button.getParent();
        TextArea textArea = (TextArea) notepane.getChildren().get(0);
        if (!textArea.isEditable()) {
            textArea.setEditable(true);
            button.setText("Save");
        } else {
            textArea.setEditable(false);
            notepane.getNote().setText(textArea.getText());
            button.setText("Edit");
        }
    };

    @FXML
    public boolean exportProject(ActionEvent event) {
        sortNotes();
        File exportFile;
        try {
            exportFile = new File("src\\Exports\\" + SingletonMediator.getInstance().getCurrentProject().getProjectTitle() + ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(exportFile));
            String noteText;

            for (Note note : SingletonMediator.getInstance().getCurrentProject().getTimeline()) {
                noteText = note.getText() + "\n";
                writer.write(noteText);
            }
            writer.close();
            return true;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return false;
        }
    }

   public void sortNotes(){
        SingletonMediator.getInstance().getCurrentProject().getTimeline().sort(Comparator.comparing(Note::getxPos));
   }

    // Does not know which Note object is selected only which notePane,
    // does not remove 320 from posX if more than one is removed at a time.
    /*
    Find the notePane connected to the remove button and deletes it from the ui and the Project LinkedList
     */
    EventHandler<ActionEvent > removeNote = event -> {

        Button button = (Button) event.getSource();
        Notepane notePane = (Notepane) button.getParent();
        Pane timeline = (Pane) notePane.getParent();

        timeline.getChildren().remove(notePane);
        SingletonMediator.getInstance().getCurrentProject().getTimeline().remove(notePane.getNote());
    };

    /*
    Prevents the user from dragging notepanes out of the timeline area
     */
    private boolean outSideParentBounds(Bounds childBounds, double newX, double newY) {

        Bounds parentBounds = timeline.getLayoutBounds();

        //check if too far left
        if (parentBounds.getMaxX() <= (newX + childBounds.getMaxX())) {
            return true;
        }

        //check if too far right
        if (parentBounds.getMinX() >= (newX + childBounds.getMinX())) {
            return true;
        }

        //check if too far down
        if (parentBounds.getMaxY() <= (newY + childBounds.getMaxY())) {
            return true;
        }

        //check if too far up
        return parentBounds.getMinY() >= (newY + childBounds.getMinY());
    }

    /*
    Tracks movement of Notepanes on the timeline
    Enables the user to drag notes on the timeline
     */
    final Delta dragDelta = new Delta();
    EventHandler<MouseEvent> dragNote = event -> {
        Notepane notePane = (Notepane) event.getSource();

        //Saves the coordinates of the pressed notepane and moves it to the front
        notePane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragDelta.x = notePane.getLayoutX() - event.getSceneX();
                dragDelta.y = notePane.getLayoutY() - event.getSceneY();
                notePane.setCursor(Cursor.MOVE);
                notePane.toFront();
            }
        });
        //Sets the new coordinates for the notepane when left click is released
        notePane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                notePane.setCursor(Cursor.HAND);
                notePane.getNote().setxPos(notePane.getLayoutX());
                notePane.getNote().setyPos(notePane.getLayoutY());
                event.consume();
            }
        });
        //Updates the new coordinates as the notepane is moved around on the timeline
        //And calls the outSideParentBounds method to ensure the notepane is not moved outside the timeline area
        notePane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double newX = mouseEvent.getSceneX() + dragDelta.x;
                double newY = mouseEvent.getSceneY() + dragDelta.y;
                if (outSideParentBounds(notePane.getLayoutBounds(), newX, newY)) {
                    return;
                }
                notePane.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
                notePane.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);

            }
        });
        //Changes the cursor to a hand icon when the mouse hovers over a notepane
        notePane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                notePane.setCursor(Cursor.HAND);
            }
        });
        //Enables the timeline to have objects on it be moved around
        timeline.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                System.out.println("drag over");
                if (event.getSource() == timeline) {
                    event.acceptTransferModes(TransferMode.MOVE);
                } else event.acceptTransferModes(TransferMode.NONE);
                event.consume();
            }
        });

        //Debugging function to see if the program detects drag correctly
        notePane.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected: ");
                event.consume();
            }
        });
    };

    /*
    Loads the notes associated to the current notebank
     */
    public void loadNotebankNotes(MouseEvent mouseEvent) {
        noteListView.getItems().clear();
        Notebank chosenNotebank = notebankList.getSelectionModel().getSelectedItem();
        if (mouseEvent.getClickCount() == 2) {
            SingletonMediator.getInstance().setCurrentNotebank(chosenNotebank);
        }
        noteListView.getItems().addAll(SingletonMediator.getInstance().getCurrentNotebank().getNotebankLinkedList());
    }

    //Deletes the current project on button press
    public void deleteProject(ActionEvent event) throws IOException, SQLException {
        SingletonMediator.getInstance().getCurrentProjectStrategy().deleteProject();
        returnToHomepage(event);
    }

    //Saves the new name to the project
    public void saveNewProjectName(ActionEvent event) throws IOException, SQLException {
        SingletonMediator.getInstance().getCurrentProjectStrategy().editProjectTitle(newProjectNameField.getText());
        SingletonMediator.getInstance().getCurrentProjectStrategy().saveProject
                    (SingletonMediator.getInstance().getCurrentProject());


        editTitlePane.setVisible(false);
        loadProjectName();
    }

    //Sets the editTitlePane to visible on button press
    public void openTitlePane(ActionEvent event) {
        editTitlePane.setVisible(true);
    }

    //Hides the editTitlePane on cancel or save press
    public void hideProjectNamePane(ActionEvent event) {
        editTitlePane.setVisible(false);
        newProjectNameField.clear();
    }
}

/*
Object we create to store x and y values for drag and drop functions
 */
class Delta {
    double x, y;
}