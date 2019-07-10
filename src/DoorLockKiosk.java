import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.stage.StageStyle;


public class DoorLockKiosk extends Application implements StageController, config {

    lang languageSelected = Language.getInstance().getSelectedLanguage();

    private GUI_start startStage = new GUI_start(this, languageSelected);
    private GUI_inUse inUseStage = new GUI_inUse(this, languageSelected);
    private GUI_doorOpen doorOpenStage = new GUI_doorOpen(this, languageSelected);
    private GUI_info infoStage = new GUI_info(this, languageSelected);
    private GUI_enter enterStage = new GUI_enter(this, languageSelected);
    private GUI_ErrorScreen errorStage = new GUI_ErrorScreen(this, languageSelected);
    private GUI_valid validStage = new GUI_valid(this, languageSelected);
    private ScriptHandler sensorHandler;

    private final Stage window = new Stage();
    private Scene scene;

    private static String lastScene;


    // TODO react to changed language. Use: overrideGUIInstances(languageSelected);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        window.setTitle("DoorLockKiosk");
        setWindowSize(window);

        // make fullscreen and unclosable! TODO uncomment!
        //window.setFullScreen(true);
        //window.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        window.setOnCloseRequest( e -> e.consume() );


        /**
         * When the application needs to be closed the processes for the scripts have to be killed
         * window.setOnCloseRequest(e -> {
         *             sensorHandler.killProcess();
         *             openHandler.killProcess();
         *             Platform.exit();
         *         });
         * @author Ertel
         */

        /*
         * This is done on starting the app: load scene "home" and then show the window.
         */
        lastScene = "home";
        goHome();
        // comment and use goto() instead for testing any screen:
        // "inUse", "info", "home", "enter", "doorOpen", "valid", anything else shows the error screen
        //goTo("inUse");
        window.show();

        /**Initilaise and start Task for executing Script and add a Listener to Message Property where the output of the script is redirected to.
         * Changes State of the Application to go to the corresponding screen
         * @author Ertel
         */


        sensorHandler = new ScriptHandler(this, config.sensorScriptPath, config.pythonPath, 3000);
        new Thread(sensorHandler).start();
        sensorHandler.messageProperty().addListener((obs, oldMsg, newMsg) -> {
            if (newMsg.equals("open")) {
                CapsuleStateContainer.getInstance().setState(CapsuleState.DOOR_OPEN);
                this.checkState();
            }
            else if(newMsg.equals("closed")) {
                CapsuleStateContainer.getInstance().setState(CapsuleState.IN_USE);
                this.checkState();
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

    /**
     * @author Julia
     * this functions takes you to what home currently looks like (depending on the capsule state): can be start, inUse or doorOpen
     */
    @Override
    public void goHome() {
        if (CapsuleStateContainer.getInstance().getState().equals(CapsuleState.FREE)) {
            System.out.println("CapsuleState: " + CapsuleStateContainer.getInstance().getState());
            goTo("home");
        } else {
            String target = CapsuleStateContainer.getInstance().getState().getString();
            goTo(target);
            System.out.println("Target: " + target);
            System.out.println("CapsuleState: " + CapsuleStateContainer.getInstance().getState());
        }
    }

    @Override
    public void goBack(){
        goTo(lastScene);
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

        window.setScene(scene);
        window.setMaximized(true);
       /* window.setFullScreen(false);
        window.setFullScreen(true);*/
    }

    private void setWindowSize(Stage window) {
        int width = (int) Screen.getPrimary().getBounds().getWidth();
        int height = (int) Screen.getPrimary().getBounds().getHeight();
        window.setWidth(width);
        window.setHeight(height);

        window.initStyle(StageStyle.UNDECORATED);

        //window.setMaximized(true);
        //window.setFullScreen(true);
    }

    /**
     * @author Julia
     * We might need this to set a different language to all scenes...
     * @param lang lang object defining the language chosen by the user
     */
    private void overrideGUIInstances(lang lang) {
        GUI_start startStage = new GUI_start(this, lang);
        GUI_inUse inUseStage = new GUI_inUse(this, lang);
        GUI_doorOpen doorOpenStage = new GUI_doorOpen(this, lang);
        GUI_info infoStage = new GUI_info(this, lang);
        GUI_enter enterStage = new GUI_enter(this, lang);
        GUI_ErrorScreen errorStage = new GUI_ErrorScreen(this, lang);
        GUI_valid validStage = new GUI_valid(this, lang);
    }
}
