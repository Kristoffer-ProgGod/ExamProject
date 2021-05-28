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
    ProjectStrategy dataManipulationStrategy = new SingleUser();


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent startPage = FXMLLoader.load(getClass().getResource("./User Interface/startPage.fxml"));
        Scene startScene = new Scene(startPage, 600, 440);

        primaryStage.setScene(startScene);
        primaryStage.setTitle("Timeline");
        primaryStage.show();



    }
}
