import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


// 1024x600 px screen

public class GUI_valid extends Stage implements StageControllerPassive{

        private Scene scene;
        private BorderPane layout;
        private StageController stageController;
        private lang languageSelected;

        //TODO put the strings as resource for translation later
        private Label validMsg = new Label("Enjoy your nap!");
        private Label msg2 = new Label("You can now enter the capsule.\nPlease don't forget to close the door!");


        // constructor
        GUI_valid(StageController stc, lang lang){
            languageSelected = lang;
            stageController = stc;
            makeLayout();
        }

        private void makeLayout(){
            layout = new BorderPane();
            VBox main = new VBox(20);
            Insets paddingStd = new Insets(20,20,20,20);

            main.getChildren().addAll(validMsg, msg2);
            main.setPadding(paddingStd);
            main.getStyleClass().add("bg_green_gradient");
            main.setAlignment(Pos.CENTER);

            validMsg.getStyleClass().addAll("h1");
            msg2.setTextAlignment(TextAlignment.CENTER);
            msg2.getStyleClass().addAll("h4");

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


