package Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {

    Pane project = new Pane();
    Pane notebank = new Pane();
    TextField projectName = new TextField();
    TextField notebankName = new TextField();
    Button editProjectButton = new Button();
    Button deleteProjectButton = new Button();
    Button editNotebankButton = new Button();
    Button deleteNotebankButton = new Button();


    @FXML
    Button addProject;

    @FXML
    Button addNotebank;

    @FXML
    AnchorPane homepagePane;


    public void addANewProject(ActionEvent event) {


        editProjectButton.setText("Edit");
        editProjectButton.setPrefSize(60, 30);
        editProjectButton.setLayoutX(0);
        editProjectButton.setLayoutY(0);
        editProjectButton.setOnAction(switchScene);

        deleteProjectButton.setText("Delete");
        deleteProjectButton.setPrefSize(65, 30);
        deleteProjectButton.setLayoutX(60);
        deleteProjectButton.setLayoutY(0);

        projectName.setPromptText("Enter Project Name");
        projectName.setPrefSize(185, 65);
        projectName.setLayoutX(7);
        projectName.setLayoutY(60);
        projectName.isEditable();


        project.setStyle("-fx-background-color: white");
        project.setPrefSize(200, 150);
        project.setLayoutX(150);
        project.setLayoutY(185);

        project.getChildren().addAll(projectName, editProjectButton, deleteProjectButton);
        homepagePane.getChildren().add(project);

    }

    public void addANewNotebank(ActionEvent event) {

        editNotebankButton.setText("Edit");
        editNotebankButton.setPrefSize(60, 30);
        editNotebankButton.setLayoutX(0);
        editNotebankButton.setLayoutY(0);
        editNotebankButton.setOnAction(switchScene);

        deleteNotebankButton.setText("Delete");
        deleteNotebankButton.setPrefSize(65, 30);
        deleteNotebankButton.setLayoutX(60);
        deleteNotebankButton.setLayoutY(0);

        notebankName.setPromptText("Enter Project Name");
        notebankName.setPrefSize(185, 65);
        notebankName.setLayoutX(7);
        notebankName.setLayoutY(60);
        notebankName.isEditable();


        notebank.setStyle("-fx-background-color: white");
        notebank.setPrefSize(200, 150);
        notebank.setLayoutX(150);
        notebank.setLayoutY(645);

        notebank.getChildren().addAll(notebankName, editNotebankButton, deleteNotebankButton);
        homepagePane.getChildren().add(notebank);

    }


    EventHandler<ActionEvent> switchScene = new EventHandler<ActionEvent>() {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Stage stage = null;
            Parent myNewScene = null;


            if (event.getSource() == editProjectButton) {
                stage = (Stage) editProjectButton.getScene().getWindow();

                try {
                    myNewScene = FXMLLoader.load(getClass().getResource("../User Interface/ProjectPage.fxml"));
                    Scene scene = new Scene(myNewScene);
                    stage.setScene(scene);
                    stage.setTitle("ProjectPage");
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (event.getSource() == editNotebankButton) {
                stage = (Stage) editNotebankButton.getScene().getWindow();

                try {
                    myNewScene = FXMLLoader.load(getClass().getResource("../User Interface/NotebankPage.fxml"));
                    Scene scene = new Scene(myNewScene);
                    stage.setScene(scene);
                    stage.setTitle("NotebankPage");
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
