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
    DataManipulationStrategy dataManipulationStrategy = new SingleUser();


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("User Interface/Homepage.fxml"));
        primaryStage.setTitle("Timeline");
        primaryStage.setScene(new Scene(root, 1700, 900));
        primaryStage.show();


        dataManipulationStrategy.save();
    }
}
