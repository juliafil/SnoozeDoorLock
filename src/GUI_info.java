import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

// 1024x600 px screen

public class GUI_info extends Stage implements StageControllerPassive {

    private StageController stageController;

    private BorderPane layout = new BorderPane();
    private VBox controls_back = new VBox(0);
    private VBox controls_scroll = new VBox(0);
    private VBox main = new VBox(20);
    private Insets paddingStd = new Insets(20,20,20,20);
    private Button backBtn = new Button();
    private Button downBtn = new Button();
    private Button upBtn = new Button();

    //TODO put the strings as resource for translation later
    private Label txt = new Label("Snooze ist Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.");
    private Label hl = new Label("The Snooze Project");

    private Scene scene;

    // constructor
    GUI_info(StageController stc){
        stageController = stc;
        makeLayout();
    }

    private void makeLayout(){
        main.getChildren().addAll(hl, txt);
        main.setPadding(paddingStd);

        controls_back.getChildren().addAll(backBtn);
        controls_scroll.setAlignment(Pos.BOTTOM_CENTER);
        controls_scroll.getChildren().addAll(upBtn, downBtn);

        txt.setTextAlignment(TextAlignment.LEFT);
        txt.setWrapText(true);
        txt.getStyleClass().addAll("h4", "leftAligned");

        hl.getStyleClass().addAll("h3");
        hl.setAlignment(Pos.CENTER);
        hl.setMaxWidth(Double.MAX_VALUE);
        hl.setTextAlignment(TextAlignment.CENTER);

        layout.setCenter(main);
        layout.setLeft(controls_back);
        layout.setRight(controls_scroll);
        layout.getStyleClass().addAll("blueBG");

        scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("resources/Style.css").toString());
        this.setScene(scene);

        backBtn.setOnAction( e -> {
            stageController.goHome();
        });
    }

    @Override
    public Scene getMyScene() {return scene;}


}
