import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
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
        HBox infoBar = new HBox(10);
        Insets paddingStd = new Insets(20,20,20,20);
        ImageView helpIcon = new ImageView("resources/questionmark.png");
        helpIcon.setSmooth(true);
        helpIcon.setCache(true);
        helpIcon.setPreserveRatio(true);
        helpIcon.setFitHeight( 30 );

        main.getChildren().add(inUseMsg);
        main.setPadding(paddingStd);
        main.getStyleClass().add("bg_brand");
        main.setAlignment(Pos.CENTER);

        infoBar.getChildren().addAll(helpIcon, helpMsg);
        infoBar.setPadding(paddingStd);
        infoBar.getStyleClass().addAll("bg_paleblue", "text_brand");

        inUseMsg.setTextAlignment(TextAlignment.CENTER);
        inUseMsg.getStyleClass().addAll("h1");
        helpMsg.setAlignment(Pos.CENTER);
        helpMsg.getStyleClass().addAll("h4");

        layout.setCenter(main);
        layout.setBottom(infoBar);

        scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("resources/Style.css").toString());
        this.setScene(scene);

        infoBar.setOnMouseClicked( e -> stageController.goTo("info") );
    }


    @Override
    public Scene getMyScene() {
        return scene;
    }
}
