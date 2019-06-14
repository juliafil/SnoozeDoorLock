import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;


public class DoorLockKiosk extends Application implements StageController, config {

    lang languageSelected = Language.getInstance().getSelectedLanguage();

    private GUI_start startStage = new GUI_start(this, languageSelected);
    private GUI_inUse inUseStage = new GUI_inUse(this, languageSelected);
    private GUI_doorOpen doorOpenStage = new GUI_doorOpen(this, languageSelected);
    private GUI_wrongCode wrongCodeStage = new GUI_wrongCode(this, languageSelected);
    private GUI_info infoStage = new GUI_info(this, languageSelected);
    private GUI_enter enterStage = new GUI_enter(this, languageSelected);
    private GUI_ErrorScreen errorStage = new GUI_ErrorScreen(this, languageSelected);
    private GUI_valid validStage = new GUI_valid(this, languageSelected);

    private final Stage window = new Stage();
    private Scene scene;


    // TODO react to changed language. Use: overrideGUIInstances(languageSelected);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // make fullscreen and unclosable! TODO uncomment!
        /*
        window.setFullScreen(true);
        window.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        window.setOnCloseRequest( e -> e.consume() );
        */

        if (CapsuleStateContainer.getInstance().getState() == CapsuleState.FREE) {
            goTo("home");
        } else {
            goTo(CapsuleStateContainer.getInstance().getState().getString());
        }


        //Initilaise and start Task for executing Script and add a Listener to Message Property
        ScriptHandler testHandler = new ScriptHandler(this, scriptPath, pythonPath);
        new Thread(testHandler).start();
        testHandler.messageProperty().addListener((obs, oldMsg, newMsg) -> {
            if (newMsg.equals("true")) {
                goTo("doorOpen");
            }
        });

    }

    /**
     * @author Edouard
     * call this function if the state of the door changed and you want to go to corresponding screeen
     */
    @Override
    public void checkState() {
        goTo(CapsuleStateContainer.getInstance().getState().getString());
    }

    @Override
    public void goHome() {
        goTo("home");
    }

    @Override
    public void goTo(String targetScene) {

        switch (targetScene) {

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
            case "valid":
                scene = validStage.getMyScene();
                break;
            case "error":
            default:
                scene = errorStage.getMyScene();
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

    private void overrideGUIInstances(lang lang) {
        GUI_start startStage = new GUI_start(this, lang);
        GUI_inUse inUseStage = new GUI_inUse(this, lang);
        GUI_doorOpen doorOpenStage = new GUI_doorOpen(this, lang);
        GUI_wrongCode wrongCodeStage = new GUI_wrongCode(this, lang);
        GUI_info infoStage = new GUI_info(this, lang);
        GUI_enter enterStage = new GUI_enter(this, lang);
        GUI_ErrorScreen errorStage = new GUI_ErrorScreen(this, lang);
        GUI_valid validStage = new GUI_valid(this, lang);
    }
}
