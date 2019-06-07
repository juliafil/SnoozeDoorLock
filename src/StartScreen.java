import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// 1024x600 px screen

public class StartScreen {

    Stage window = new Stage();
    Scene scene;

    BorderPane layout = new BorderPane();
    VBox infoSide = new VBox(20);
    VBox enterSide = new VBox(20);
    HBox welcomeBar = new HBox(0);

    //TODO put the strings as resource for translation later
    Label welcomeMsg = new Label("Tired from learning? Get some sleep with Snooze!");
    Label infoMsg = new Label("What is this?");
    Label enterMsg = new Label("I have a code, let me sleep!");

    Insets paddingStd = new Insets(20,20,20,20);


    // constructor
    StartScreen(){
        makeLayout();
    }

    private void makeLayout(){
        welcomeBar.getChildren().add(welcomeMsg);
        welcomeBar.setPadding(paddingStd);
        welcomeBar.getStyleClass().add("welcomeBar");
        infoSide.getChildren().add(infoMsg);
        infoSide.getStyleClass().add("infoPanel");
        infoSide.setPadding(paddingStd);
        infoSide.setPrefWidth(350);
        enterSide.getChildren().add(enterMsg);
        enterSide.getStyleClass().add("enterPanel");
        enterSide.setPadding(paddingStd);

        layout.setLeft(infoSide);
        layout.setCenter(enterSide);
        layout.setTop(welcomeBar);

        scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("resources/Style.css").toString());
    }

    Stage getStage(){
        return window;
    }

    Scene getScene(){
        return scene;
    }



}
