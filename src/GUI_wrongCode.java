import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// 1024x600 px screen

public class GUI_wrongCode extends Stage implements StageControllerPassive {

    private BorderPane layout = new BorderPane();
    private VBox main = new VBox(20);
    private Insets paddingStd = new Insets(20,20,20,20);

    private StageController stageController;
    private Scene scene;

    //TODO put the strings as resource for translation later
    private Label msg = new Label("Wrong code.");
    private Label msg2 = new Label("Try again!");
    private Label hint = new Label("You can find the code under \"bookings\" in your Snooze app.");

    // constructor
    GUI_wrongCode(StageController stc){
        stageController = stc;
        makeLayout();
    }

    private void makeLayout(){
        msg2.getStyleClass().addAll("h2");
        hint.getStyleClass().addAll("h4");
        msg.getStyleClass().addAll("h1");

        msg.setAlignment(Pos.CENTER);
        msg2.setAlignment(Pos.CENTER);
        hint.setAlignment(Pos.CENTER);

        main.getChildren().addAll(msg, msg2, hint);
        main.setPadding(paddingStd);
        main.getStyleClass().addAll("error1_BG");

        layout.setCenter(main);

        scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("resources/Style.css").toString());
    }

    @Override
    public Scene getMyScene() {return scene;}


}
