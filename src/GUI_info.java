import com.sun.prism.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

// 1024x600 px screen

public class GUI_info extends Stage implements StageControllerPassive {

    private StageController stageController;
    private lang languageSelected;
    private Scene scene;
    private BorderPane layout;

    private final int scrollIncrement = 10;

    // constructor
    GUI_info(StageController stc, lang lang){
        languageSelected = lang;
        stageController = stc;
        makeLayout();
    }

    private void makeLayout(){
        // make components
        layout = new BorderPane();
        VBox controls_back = new VBox(0);
        VBox controls_scroll = new VBox(5);
        VBox main = new VBox(20);
        Insets paddingStd = new Insets(20,20,20,20);
        Button backBtn = new Button();
        Button downBtn = new Button();
        Button upBtn = new Button();
        VBox scrollableContent = new VBox(20);

        ImageView logo = new ImageView("resources/logo_white_medium.png");
        logo.setSmooth(true);
        logo.setCache(true);
        logo.setPreserveRatio(true);
        logo.setFitHeight( 100 );
        HBox logoWrapper = new HBox(0);
        logoWrapper.getChildren().add(logo);
        logoWrapper.setAlignment(Pos.CENTER);
        logoWrapper.setPrefHeight(100);

        //logoWrapper.setBackground(new Background(new BackgroundFill(Paint.valueOf("#fff"), null, null)));

        //TODO put the strings as resource for translation later
        Text txt = new Text("Snooze ist Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt. Snooze ist Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla. Snooze ist Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.");
        Label headline = new Label("The Snooze Project");

        Pane txtWrapper = new Pane(txt);

        // make Buttons squares
        Button[] btns = {backBtn, downBtn, upBtn};
        for( Button b : btns ){
            b.setPrefHeight(80);
            b.setPrefWidth(80);
        }
        upBtn.getStyleClass().addAll("up_button");
        downBtn.getStyleClass().addAll("down_button");
        backBtn.getStyleClass().addAll("back_button");

        // assemble components
        headline.setPadding(new Insets(0, 0, 20, 0));
        scrollableContent.getChildren().addAll(logoWrapper, headline, txtWrapper);
        ScrollPane scroller = new ScrollPane(scrollableContent);
        main.getChildren().addAll(scroller);
        main.setPadding(new Insets(20, 60, 20, 60));

        controls_back.getChildren().addAll(backBtn);
        controls_scroll.setAlignment(Pos.BOTTOM_CENTER);
        controls_scroll.getChildren().addAll(upBtn, downBtn);

        scroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroller.getStyleClass().addAll("scroller");

        scrollableContent.setPrefHeight(Screen.getPrimary().getBounds().getHeight());

        txt.wrappingWidthProperty().bind(scroller.widthProperty());
        txt.getStyleClass().addAll("h4", "leftAligned");
        txt.setLineSpacing(2.5);
        txt.setFill(Paint.valueOf("#ffffff"));

        headline.getStyleClass().addAll("h3");
        headline.setAlignment(Pos.CENTER);
        headline.setMaxWidth(Double.MAX_VALUE);
        headline.setTextAlignment(TextAlignment.CENTER);

        layout.setCenter(main);
        layout.setLeft(controls_back);
        layout.setRight(controls_scroll);
        layout.getStyleClass().addAll("bg_brand_humble");

        scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("resources/Style.css").toString());
        this.setScene(scene);

        // back btn touch
        //backBtn.setOnTouchPressed( e -> stageController.goHome());
        // back btn click
        backBtn.setOnAction( e -> stageController.goBack());

        // scroll buttons functionality click
        scroller.setVvalue(0);
        upBtn.setOnAction( e -> {
            if (scroller.getVvalue() > scroller.getVmin()) {
                scroller.setVvalue(scroller.getVvalue() - scrollIncrement);
            }
        });

        downBtn.setOnAction( e -> {
            if (scroller.getVvalue() < scroller.getVmax()) {
                scroller.setVvalue(scroller.getVvalue() + scrollIncrement);
            }
        });

        // scroll buttons functionality touch
        /*scroller.setVvalue(scroller.getVmax());
        upBtn.setOnTouchPressed( e -> {
            if (scroller.getVvalue() > scroller.getVmin()) {
                scroller.setVvalue(scroller.getVvalue() - scrollIncrement);
            }
        });

        downBtn.setOnTouchPressed( e -> {
            if (scroller.getVvalue() < scroller.getVmax()) {
                scroller.setVvalue(scroller.getVvalue() + scrollIncrement);
            }
        });
        */
    }

    @Override
    public Scene getMyScene() {return scene;}


}
