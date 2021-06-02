import Domain.Note;
import Domain.Notebank;
import Domain.Project;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Application.*;

import java.util.ArrayList;

public class Main extends Application {

    ArrayList<Project> projectArrayList = new ArrayList<>();
    ArrayList<Notebank> notebankArrayList = new ArrayList<>();
    ProjectStrategy singleProjectStrategy = new SingleUser();
    ProjectStrategy multiProjectStrategy = new MultiUser();
    NotebankStrategy singleNotebankStrategy = new SingleUser();
    NotebankStrategy multiNotebankStrategy = new MultiUser();


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent startPage = FXMLLoader.load(getClass().getResource("./User Interface/startPage.fxml"));
        Scene startScene = new Scene(startPage, 600, 440);

        primaryStage.setScene(startScene);
        primaryStage.setTitle("Timeline");
        primaryStage.show();
//
        Project project = new Project("First", "First Timeline", 0);
        project.getTimeline().add(new Note("First", "Robot captain"));
        project.getTimeline().add(new Note("Second", "WAZUUUUUUUP"));
        project.getTimeline().add(new Note("Third", "I double dare you motherfucker"));

        singleProjectStrategy.saveProject(project);


        Project project2 = new Project("Second", "Second Timeline", 1);
        project2.getTimeline().add(new Note("Badabing badabom", "Say hello to my little friend"));

        singleProjectStrategy.saveProject(project2);

//        projectArrayList = singleStrategy.getAllProject();

//
//        Notebank notebank1 = new Notebank(1, "First Notebank");
//        Notebank notebank2 = new Notebank(1, "Second Notebank");
//
//        singleNotebankStrategy.saveNotebank(notebank1);
//        singleNotebankStrategy.saveNotebank(notebank2);
    }

}
