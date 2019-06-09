import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// 1024x600 px screen

public class GUI_doorOpen extends Stage implements StageControllerPassive {

    private BorderPane layout = new BorderPane();
    private VBox main = new VBox(20);
    private Insets paddingStd = new Insets(20,20,20,20);

    private Scene scene;
    private StageController stageController;

    //TODO put the strings as resource for translation later
    private Label msg = new Label("Please close the door!");

    // constructor
    GUI_doorOpen(StageController stc){
        stageController = stc;
        makeLayout();
    }

    private void makeLayout(){
        main.getChildren().add(msg);
        main.setPadding(paddingStd);
        main.getStyleClass().addAll("error2_BG");
        main.setAlignment(Pos.CENTER);

        msg.setAlignment(Pos.CENTER);
        msg.getStyleClass().addAll("h1");

        layout.setCenter(main);

        scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("resources/Style.css").toString());
    }

    @Override
    public Scene getMyScene() {return scene;}




}
