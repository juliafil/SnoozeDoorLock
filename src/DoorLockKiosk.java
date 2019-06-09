import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;


public class DoorLockKiosk extends Application implements StageController {

    // instantiate all GUI classes
    private final GUI_start startStage = new GUI_start(this);
    private final GUI_inUse inUseStage = new GUI_inUse(this);
    private final GUI_doorOpen doorOpenStage = new GUI_doorOpen(this);
    private final GUI_wrongCode wrongCodeStage = new GUI_wrongCode(this);
    private final GUI_info infoStage = new GUI_info(this);
    private final GUI_enter enterStage = new GUI_enter(this);

    private final Stage window = new Stage();
    private Scene scene;

    Logic logic = new Logic();

    // TODO make enum for switch screens (see switch case in @Override start() )

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        goTo("home");
    }

    @Override
    public void goHome() {
        goTo("home");
    }

    @Override
    public void goTo(String targetScene) {

        switch (targetScene){

            case "home":
                scene = startStage.getMyScene();
                break;

            case "inUse":
                scene = inUseStage.getMyScene();
                break;

            case "doorOpen":
                scene = doorOpenStage.getMyScene();
                break;

            case "wrongCode":
                scene = wrongCodeStage.getMyScene();
                break;

            case "info":
                scene = infoStage.getMyScene();
                break;

            case "enter":
                scene = enterStage.getMyScene();
                break;

        }
        window.setTitle("DoorLockKiosk");
        setWindowSize(window);
        window.setScene(scene);
        window.show();
    }

    private void setWindowSize(Stage window) {
        window.setWidth(1024);
        window.setHeight(600);
    }
}
