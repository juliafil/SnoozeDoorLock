import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// 1024x600 px screen

public class GUI_doorOpen extends Stage implements StageControllerPassive {

    private Scene scene;
    private BorderPane layout;
    private StageController stageController;
    lang languageSelected;

    //TODO put the strings as resource for translation later
    private Label msg = new Label("Please close the door!");

    // constructor
    GUI_doorOpen(StageController stc, lang lang){
        languageSelected = lang;
        stageController = stc;
        makeLayout();
    }

    private void makeLayout(){
        layout = new BorderPane();
        VBox main = new VBox(20);
        Insets paddingStd = new Insets(20,20,20,20);

        main.getChildren().add(msg);
        main.setPadding(paddingStd);
        main.getStyleClass().addAll("error2_BG");
        main.setAlignment(Pos.CENTER);

        msg.setAlignment(Pos.CENTER);
        msg.getStyleClass().addAll("h1");

        layout.setCenter(main);

        scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("resources/Style.css").toString());
        this.setScene(scene);
    }

    @Override
    public Scene getMyScene() {return scene;}

}
