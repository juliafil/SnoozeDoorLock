import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// 1024x600 px screen

public class GUI_ErrorScreen extends Stage implements StageControllerPassive {

    private Scene scene;
    private StageController stageController;
    private lang languageSelected;

    //TODO put the strings as resource for translation later
    private Label errorMsg = new Label("Some error occurred.");
    private Label hint = new Label("Please tell our staff!");


    // constructor
    GUI_ErrorScreen(StageController stc, lang lang){
        languageSelected = lang;
        stageController = stc;
        makeLayout();
    }

    private void makeLayout(){
        BorderPane layout = new BorderPane();
        VBox main = new VBox(20);
        Insets paddingStd = new Insets(20,20,20,20);

        main.getChildren().addAll(errorMsg, hint);
        main.setPadding(paddingStd);
        main.getStyleClass().add("error1_BG");
        main.setAlignment(Pos.CENTER);

        errorMsg.setAlignment(Pos.CENTER);
        errorMsg.getStyleClass().addAll("h1");
        hint.setAlignment(Pos.CENTER);
        hint.getStyleClass().addAll("h4");

        layout.setCenter(main);

        scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("resources/Style.css").toString());
        this.setScene(scene);
    }


    @Override
    public Scene getMyScene() {
        return scene;
    }
}
