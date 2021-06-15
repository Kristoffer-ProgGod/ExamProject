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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {

    //Arraylists for storing Project and Notebank Objects
    public static ArrayList<Project> projects;
    public static ArrayList<Notebank> notebanks;

    //UI controls
    Pane projectPane = new Pane();
    Pane notebankPane = new Pane();
    TextField projectName = new TextField();
    TextField notebankName = new TextField();
    Button createProjectButton = new Button();
    Button cancelProjectButton = new Button();
    Button createNotebankButton = new Button();
    Button cancelNotebankButton = new Button();


    //Button that controls adding new Project Objects
    @FXML
    Button addProject;

    //Button that controls adding new Notebank objects
    @FXML
    Button addNotebank;

    //The main pane that displays the homepage
    @FXML
    AnchorPane homepagePane;

    //Initializes a new pane that opens in the current window with buttons and textFields to create a Project object
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

    //Initializes a new pane that opens in the current window with buttons and textFields to create a Notebank object
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

    //Checks which object type is being made (Notebank or Project), initializes it and opens their respective page
    EventHandler<ActionEvent> switchScene = new EventHandler<ActionEvent>() {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Stage stage = null;
            Parent myNewScene = null;


            if (event.getSource() == createProjectButton) {
                stage = (Stage) createProjectButton.getScene().getWindow();
                Project project = new Project(projectName.getText());
                SingletonMediator.getInstance().setCurrentProject(project);
                if (SingletonMediator.getInstance().getCurrentProjectStrategy().equals(StartPageController.multiProjectStrategy)) {
                    SingletonMediator.getInstance().getCurrentProject().setProjectId();
                }
                try {
                    SingletonMediator.getInstance().getCurrentProjectStrategy().createProject(SingletonMediator.getInstance().getCurrentProject());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    System.out.println("Cannot create project");
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
                if (SingletonMediator.getInstance().getCurrentNotebankStrategy().equals(StartPageController.multiNotebankStrategy)) {
                    SingletonMediator.getInstance().getCurrentNotebank().setNotebankId();
                }
                try {
                    SingletonMediator.getInstance().getCurrentNotebankStrategy().createNotebank(SingletonMediator.getInstance().getCurrentNotebank());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    System.out.println("Cannot create notebank");

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

    /*
    This method handles clicking on existing projects and notebanks,
    passes them to the mediator and opens their respective windows
     */
    EventHandler<ActionEvent> openPane = event -> {
        if (event.getSource() == addProject) {
            projectPane.setVisible(true);
        }
        if (event.getSource() == addNotebank) {
            notebankPane.setVisible(true);
        }
    };

    /*
    Depending on user strategy (SingleUser or MultiUser)
    Loads all existing projects and notebanks then calls methods to display them on the page.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            projects = (SingletonMediator.getInstance().getCurrentProjectStrategy().getAllProject());
            notebanks = (SingletonMediator.getInstance().getCurrentNotebankStrategy().getAllNotebank());
            if (projects != null) {
                Button[] projectButtons = new Button[projects.size()];
                generateProjectList(projects, projectButtons);
            }
            if (notebanks != null) {
                Button[] notebankButtons = new Button[notebanks.size()];
                generateNotebankList(notebanks, notebankButtons);
            }
            if (projects == null) {
                projects = new ArrayList<>();
            }
            if (notebanks == null) {
                notebanks = new ArrayList<>();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
        }

    }

    /*
    @param ArrayList<Project> holds all projects that have been loaded when the page is initialized.
    @param Button[] array that holds empty button objects
    Initializes button objects using the arrayList so the user later can choose which project to work with
    by adding each project name to a single button
     */
    public void generateProjectList(ArrayList<Project> projects, Button[] buttons) {
        int xPos = 200;
        String projectName;

        for (int i = 0; i < projects.size(); i++) {
            projectName = projects.get(i).getProjectTitle();

            buttons[i] = new Button();
            buttons[i].setPrefSize(150, 150);
            buttons[i].setLayoutX(xPos);
            buttons[i].setLayoutY(200);
            buttons[i].setText(projectName);
            buttons[i].setOnAction(openProject);
            xPos = xPos + 200;
            buttons[i].setStyle("-fx-font-weight: bold");
            homepagePane.getChildren().add(buttons[i]);
        }
    }

    /*
    @param ArrayList<Notebank> holds all notebanks that have been loaded when the page is initialized
    @param Button[] array that holds empty button objects
    Initializes button objects using the arrayList so the user later can choose which notebank to work with
    by adding each notebank name to a single button
     */
    public void generateNotebankList(ArrayList<Notebank> notebanks, Button[] buttons) {
        int xPos = 200;
        String notebankName;

        for (int i = 0; i < notebanks.size(); i++) {
            notebankName = notebanks.get(i).getNotebankTitle();

            buttons[i] = new Button();
            buttons[i].setPrefSize(150, 150);
            buttons[i].setLayoutX(xPos);
            buttons[i].setLayoutY(740);
            buttons[i].setText(notebankName);
            buttons[i].setOnAction(openNotebank);
            xPos = xPos + 200;
            buttons[i].setStyle("-fx-font-weight: bold");
            homepagePane.getChildren().add(buttons[i]);
        }
    }


    //Opens the selected project
    EventHandler<ActionEvent> openProject = event -> {

        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        Parent myNewScene;

        try {
            SingletonMediator.getInstance().setCurrentProject(findProject(projects, button.getText()));
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
            SingletonMediator.getInstance().setCurrentNotebank(findNotebank(notebanks, button.getText()));
            myNewScene = FXMLLoader.load(getClass().getResource("../User Interface/NotebankPage.fxml"));
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.setTitle("NotebankPage");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    /*
    @param ArrayList<Project> an arrayList that holds all the projects in the program
    @param String projectName, the name of the of the requested project
    Looks through the arrayList of projects to see if any of them match the projectName of the requested project
    If they match the project is set as the currently selected project
     */
    public Project findProject(ArrayList<Project> projects, String projectName) {
        Project selected = null;

        try {

            for (Project project : projects) {
                if (project.getProjectTitle().equals(projectName)) {
                    selected = project;
                }
            }
            return selected;
        } catch (Exception e) {
            e.printStackTrace();
            return selected;
        }
    }

    /*
    @param ArrayList<Notebank> an arrayList that holds all the notebanks in the program
    @param String notebankName, the name of the of the requested notebank
    Looks through the arrayList of notebanks to see if any of them match the notebankName of the requested notebank
    If they match the notebank is set as the currently selected notebank
     */
    public Notebank findNotebank(ArrayList<Notebank> notebanks, String notebankName) {
        Notebank selected = null;

        try {

            for (Notebank notebank : notebanks) {
                if (notebank.getNotebankTitle().equals(notebankName)) {
                    selected = notebank;
                }
            }
            return selected;
        } catch (Exception e) {
            e.printStackTrace();
            return selected;
        }
    }
}
