import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class DoorLockKiosk extends Application {

    StartScreen startScreen = new StartScreen();
    Stage primaryStage = startScreen.getStage();
    Scene scene = startScreen.getScene();

    Logic logic = new Logic();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("DoorLockKiosk");
        primaryStage.setScene(scene);
        //scene.getStylesheets().add("../resources/Style.css");
        primaryStage.setWidth(1024);
        primaryStage.setHeight(600);
        primaryStage.show();
    }

}
