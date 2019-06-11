import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// 1024x600 px screen

public class GUI_start extends Stage implements StageControllerPassive {

    private StageController stageController;
    private Scene scene;
    private lang languageSelected;


    //TODO put the strings as resource for translation later
    private Label welcomeMsg = new Label("Tired from learning? Get some sleep with Snooze!");
    private Label infoMsg = new Label("What is this?");
    private Label enterMsg = new Label("I have a code, let me sleep!");

    private Insets paddingStd = new Insets(20,20,20,20);


    // constructor
    GUI_start(StageController stc, lang lang){
        languageSelected = lang;
        stageController = stc;
        makeLayout();
    }

    private void makeLayout(){
        BorderPane layout = new BorderPane();
        VBox infoSide = new VBox(20);
        VBox enterSide = new VBox(20);
        HBox welcomeBar = new HBox(0);

        welcomeBar.getChildren().add(welcomeMsg);
        welcomeBar.setPadding(paddingStd);
        welcomeBar.getStyleClass().addAll("welcomeBar", "blueText");
        welcomeBar.setAlignment(Pos.CENTER);
        welcomeMsg.setAlignment(Pos.CENTER);
        welcomeMsg.getStyleClass().addAll("blueText", "h5");

        infoSide.getChildren().add(infoMsg);
        infoSide.getStyleClass().addAll("infoPanel", "paleblueBG");
        infoSide.setPadding(paddingStd);
        infoSide.setAlignment(Pos.CENTER);
        infoSide.setPrefWidth(350);
        infoMsg.setAlignment(Pos.CENTER);
        infoMsg.getStyleClass().addAll("h2");

        enterSide.getChildren().add(enterMsg);
        enterSide.getStyleClass().addAll("enterPanel", "blueBG");
        enterSide.setPadding(paddingStd);
        enterSide.setAlignment(Pos.CENTER);
        enterMsg.setAlignment(Pos.CENTER);
        enterMsg.getStyleClass().addAll("h2");

        layout.setLeft(infoSide);
        layout.setCenter(enterSide);
        layout.setTop(welcomeBar);

        scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("resources/Style.css").toString());
        this.setScene(scene);

        infoSide.setOnMouseClicked( e -> {
            stageController.goTo("info");
        });

        enterSide.setOnMouseClicked( e -> {
            stageController.goTo("enter");
        });
    }

    @Override
    public Scene getMyScene() {return scene;}
}
