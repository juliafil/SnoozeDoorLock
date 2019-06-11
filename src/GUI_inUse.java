import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// 1024x600 px screen

public class GUI_inUse extends Stage implements StageControllerPassive {

    private Scene scene;
    private StageController stageController;
    private lang languageSelected;

    //TODO put the strings as resource for translation later
    private Label inUseMsg = new Label("This capsule is currently in use.");
    private Label helpMsg = new Label("What is this?");


    // constructor
    GUI_inUse(StageController stc, lang lang){
        languageSelected = lang;
        stageController = stc;
        makeLayout();
    }

    private void makeLayout(){
        BorderPane layout = new BorderPane();
        VBox main = new VBox(20);
        VBox infoBar = new VBox(20);
        Insets paddingStd = new Insets(20,20,20,20);

        main.getChildren().add(inUseMsg);
        main.setPadding(paddingStd);
        main.getStyleClass().add("blueBG");

        infoBar.getChildren().add(helpMsg);
        infoBar.setPadding(paddingStd);
        infoBar.getStyleClass().addAll("paleblueBG");

        inUseMsg.setAlignment(Pos.CENTER);
        inUseMsg.getStyleClass().addAll("h1");
        helpMsg.setAlignment(Pos.CENTER);
        helpMsg.getStyleClass().addAll("h4");

        layout.setCenter(main);
        layout.setBottom(infoBar);

        scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("resources/Style.css").toString());
        this.setScene(scene);
    }


    @Override
    public Scene getMyScene() {
        return scene;
    }
}
