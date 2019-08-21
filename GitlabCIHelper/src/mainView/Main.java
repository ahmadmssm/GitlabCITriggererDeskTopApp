package mainView;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.SingleInstanceApp;


public class Main extends SingleInstanceApp {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("Starting instance " + instanceId);
        //
        Platform.setImplicitExit(true);
        //
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("Gitlab CI pipeline configurator");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void stop() {
        System.out.println("Exiting instance " + instanceId);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
