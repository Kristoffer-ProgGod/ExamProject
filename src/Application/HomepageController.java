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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {

    Pane project = new Pane();
    Pane notebank = new Pane();
    TextField projectName = new TextField();
    TextField notebankName = new TextField();
    Button createProjectButton = new Button();
    Button cancelProjectButton = new Button();
    Button createNotebankButton = new Button();
    Button cancelNotebankButton = new Button();


    @FXML
    Button addProject;

    @FXML
    Button addNotebank;

    @FXML
    AnchorPane homepagePane;






    public void addANewProject(ActionEvent event) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0,0,0));

        Image picture = new Image("./User Interface/disco-dba865f1.png");

        ImageView background = new ImageView();
        background.setFitHeight(150);
        background.setFitWidth(200);
        background.setImage(picture);

        createProjectButton.setText("Create");
        createProjectButton.setPrefSize(70, 30);
        createProjectButton.setLayoutX(0);
        createProjectButton.setLayoutY(0);
        createProjectButton.setOnAction(switchScene);
        createProjectButton.setStyle("-fx-font-weight: bolder");


        cancelProjectButton.setText("Cancel");
        cancelProjectButton.setPrefSize(70, 30);
        cancelProjectButton.setLayoutX(65);
        cancelProjectButton.setLayoutY(0);
        cancelProjectButton.setStyle("-fx-font-weight: bolder");

        projectName.setPromptText("Enter Project Name");
        projectName.setPrefSize(185, 65);
        projectName.setLayoutX(7);
        projectName.setLayoutY(60);
        projectName.isEditable();


        project.setStyle("-fx-background-color: white");
        project.setPrefSize(200, 150);
        project.setLayoutX(150);
        project.setLayoutY(185);
        project.setEffect(dropShadow);

        project.getChildren().addAll(background, projectName, createProjectButton, cancelProjectButton);
        homepagePane.getChildren().add(project);

    }

    public void addANewNotebank(ActionEvent event) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0,0,0));

        Image picture = new Image("./User Interface/disco-dba865f1.png");

        ImageView background = new ImageView();
        background.setFitHeight(150);
        background.setFitWidth(200);
        background.setImage(picture);

        createNotebankButton.setText("Create");
        createNotebankButton.setPrefSize(70, 30);
        createNotebankButton.setLayoutX(0);
        createNotebankButton.setLayoutY(0);
        createNotebankButton.setOnAction(switchScene);
        createNotebankButton.setStyle("-fx-font-weight: bolder");

        cancelNotebankButton.setText("Cancel");
        cancelNotebankButton.setPrefSize(70, 30);
        cancelNotebankButton.setLayoutX(60);
        cancelNotebankButton.setLayoutY(0);
        cancelNotebankButton.setStyle("-fx-font-weight: bolder");

        notebankName.setPromptText("Enter Project Name");
        notebankName.setPrefSize(185, 65);
        notebankName.setLayoutX(7);
        notebankName.setLayoutY(60);
        notebankName.isEditable();


        notebank.setStyle("-fx-background-color: white");
        notebank.setPrefSize(200, 150);
        notebank.setLayoutX(150);
        notebank.setLayoutY(645);
        notebank.setEffect(dropShadow);


        notebank.getChildren().addAll(background, notebankName, createNotebankButton, cancelNotebankButton);
        homepagePane.getChildren().add(notebank);

    }


    EventHandler<ActionEvent> switchScene = new EventHandler<ActionEvent>() {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Stage stage = null;
            Parent myNewScene = null;


            if (event.getSource() == createProjectButton) {
                stage = (Stage) createProjectButton.getScene().getWindow();

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
            else if (event.getSource() == createNotebankButton) {
                stage = (Stage) createNotebankButton.getScene().getWindow();

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
