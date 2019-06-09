import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// 1024x600 px screen

public class GUI_enter extends Stage implements StageControllerPassive {

    private BorderPane layout = new BorderPane();
    private VBox main = new VBox(20);
    private VBox infoBar = new VBox(20);
    private VBox controls_back = new VBox(0);
    private Button backBtn = new Button();
    private Insets paddingStd = new Insets(20,20,20,20);

    private Scene scene;
    private StageController stageController;

    //TODO put the strings as resource for translation later
    private Label msg = new Label("Please enter the 4 digit code you find in your app.");


    // constructor
    GUI_enter(StageController stc){
        stageController = stc;
        makeLayout();
    }

    private void makeLayout(){
        main.getChildren().add(msg);
        main.setPadding(paddingStd);
        main.setAlignment(Pos.TOP_CENTER);

        msg.setAlignment(Pos.CENTER);
        msg.getStyleClass().addAll("h5");

        controls_back.getChildren().addAll(backBtn);

        layout.setCenter(main);
        layout.setLeft(controls_back);
        layout.getStyleClass().addAll("blueBG");

        scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("resources/Style.css").toString());
        this.setScene(scene);

        backBtn.setOnAction( e -> {
            stageController.goHome();
        });
    }


    @Override
    public Scene getMyScene() {
        return scene;
    }
}
