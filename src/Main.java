import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("User Interface/Homepage.fxml"));
        primaryStage.setTitle("Timeline");
        primaryStage.setScene(new Scene(root, 1700, 900));
        primaryStage.show();
    }
}
