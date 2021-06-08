package Application;

import Domain.Notebank;
import Domain.Project;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {

    public static ArrayList<Project> projects;
    public static ArrayList<Notebank> notebanks;
    int maxId;

    Pane projectPane = new Pane();
    Pane notebankPane = new Pane();
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
        dropShadow.setColor(Color.color(0, 0, 0));

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
        cancelProjectButton.setOnAction(closePane);

        projectName.setPromptText("Enter Project Name");
        projectName.setPrefSize(185, 65);
        projectName.setLayoutX(7);
        projectName.setLayoutY(60);
        projectName.isEditable();


        projectPane.setStyle("-fx-background-color: white");
        projectPane.setPrefSize(200, 150);
        projectPane.setLayoutX(150);
        projectPane.setLayoutY(185);
        projectPane.setEffect(dropShadow);

        projectPane.getChildren().addAll(background, projectName, createProjectButton, cancelProjectButton);
        homepagePane.getChildren().add(projectPane);

        addProject.setOnAction(openPane);

    }

    public void addANewNotebank(ActionEvent event) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0, 0, 0));

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
        cancelNotebankButton.setOnAction(closePane);

        notebankName.setPromptText("Enter Notebank Name");
        notebankName.setPrefSize(185, 65);
        notebankName.setLayoutX(7);
        notebankName.setLayoutY(60);
        notebankName.isEditable();


        notebankPane.setStyle("-fx-background-color: white");
        notebankPane.setPrefSize(200, 150);
        notebankPane.setLayoutX(150);
        notebankPane.setLayoutY(645);
        notebankPane.setEffect(dropShadow);


        notebankPane.getChildren().addAll(background, notebankName, createNotebankButton, cancelNotebankButton);
        homepagePane.getChildren().add(notebankPane);

        addNotebank.setOnAction(openPane);
    }


    EventHandler<ActionEvent> switchScene = new EventHandler<ActionEvent>() {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Stage stage = null;
            Parent myNewScene = null;


            if (event.getSource() == createProjectButton) {
                stage = (Stage) createProjectButton.getScene().getWindow();
                SingletonMediator.getInstance().setCurrentProject(new Project(projectName.getText()));
                if(SingletonMediator.getInstance().getCurrentProjectStrategy().equals(StartPageController.multiProjectStrategy)){
                    SingletonMediator.getInstance().getCurrentProject().setProjectId();
                }
                projects.add(SingletonMediator.getInstance().getCurrentProject());

                try {
                    myNewScene = FXMLLoader.load(getClass().getResource("../User Interface/ProjectPage.fxml"));
                    Scene scene = new Scene(myNewScene);
                    stage.setScene(scene);
                    stage.setTitle("ProjectPage");
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (event.getSource() == createNotebankButton) {
                stage = (Stage) createNotebankButton.getScene().getWindow();
                SingletonMediator.getInstance().setCurrentNotebank(new Notebank(notebankName.getText()));
                if(SingletonMediator.getInstance().getCurrentNotebankStrategy().equals(StartPageController.multiNotebankStrategy)){
                    SingletonMediator.getInstance().getCurrentNotebank().setNotebankId();
                }
                notebanks.add(SingletonMediator.getInstance().getCurrentNotebank());

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

    /*
      This method finds the pane associated
      with a given button and closes it.
     */
    EventHandler<ActionEvent> closePane = event -> {
        //Takes the event source and casts it to a button
        Button button = (Button) event.getSource();
        //Takes the parent of the button and casts it as a pane
        Pane pane = (Pane) button.getParent();
        //Closes the pane
        pane.setVisible(false);
    };

    EventHandler<ActionEvent> openPane = event -> {
        if (event.getSource() == addProject) {
            projectPane.setVisible(true);
        }
        if (event.getSource() == addNotebank) {
            notebankPane.setVisible(true);
        }
    };


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProjectStrategy ps = new SingleUser();
        NotebankStrategy ns = new SingleUser();
        projects = ps.getAllProject();
        Button[] projectButtons = new Button[projects.size()];

        notebanks = ns.getAllNotebank();
        Button[] notebankButtons = new Button[notebanks.size()];


        generateProjectList(projects, projectButtons);
        generateNotebankList(notebanks, notebankButtons);
    }

    public void generateProjectList(ArrayList<Project> projects, Button[] buttons) {
        int xPos = 200;
        String projectName;
        int projectId;

        for (int i = 0; i < projects.size(); i++) {
            projectName = projects.get(i).getProjectTitle();
            projectId = projects.get(i).getProjectId();

            buttons[i] = new Button();
            buttons[i].setPrefSize(100, 100);
            buttons[i].setLayoutX(xPos);
            buttons[i].setLayoutY(200);
            buttons[i].setText(projectName);
            buttons[i].setId(String.valueOf(projectId));
            buttons[i].setOnAction(openProject);
            xPos = xPos + 150;
            homepagePane.getChildren().add(buttons[i]);
        }
    }

    public void generateNotebankList(ArrayList<Notebank> notebanks, Button[] buttons) {
        int xPos = 200;
        String notebankName;
        int notebankId;

        for (int i = 0; i < notebanks.size(); i++) {
            notebankName = notebanks.get(i).getNotebankTitle();
            notebankId = notebanks.get(i).getNotebankId();

            buttons[i] = new Button();
            buttons[i].setPrefSize(100, 100);
            buttons[i].setLayoutX(xPos);
            buttons[i].setLayoutY(740);
            buttons[i].setText(notebankName);
            buttons[i].setId(String.valueOf(notebankId));
            buttons[i].setOnAction(openNotebank);
            xPos = xPos + 150;
            homepagePane.getChildren().add(buttons[i]);
        }
    }




    //Opens the selected project
    EventHandler<ActionEvent> openProject = event -> {

        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        Parent myNewScene;

        try {
            SingletonMediator.getInstance().setCurrentProject(projects.get(Integer.parseInt(button.getId())));
            myNewScene = FXMLLoader.load(getClass().getResource("../User Interface/ProjectPage.fxml"));
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.setTitle("ProjectPage");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    //Opens the selected notebank
    EventHandler<ActionEvent> openNotebank = event -> {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        Parent myNewScene;


        try {
            SingletonMediator.getInstance().setCurrentNotebank(notebanks.get(Integer.parseInt(button.getId())));
            myNewScene = FXMLLoader.load(getClass().getResource("../User Interface/NotebankPage.fxml"));
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.setTitle("NotebankPage");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    };
}
