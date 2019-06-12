import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


// 1024x600 px screen

public class GUI_enter extends Stage implements StageControllerPassive {

    private Scene scene;
    private StageController stageController;
    private lang languageSelected;

    //TODO change back to 4, for testing the first PIN got from the website is length 3
    private int codeLength = 3;

    private Label onOkayMsg = new Label();

    CodeChecker codeChecker = new CodeChecker();

    // constructor
    GUI_enter(StageController stc, lang lang){
        languageSelected = lang;
        stageController = stc;
        makeLayout();
    }

    private VBox makeKeyboard( TextField textField ) {
        // make keyboard
        VBox keyboard = new VBox(20);

        HBox kb_row1 = new HBox(20);
        HBox kb_row2 = new HBox(20);
        HBox kb_row3 = new HBox(20);
        HBox kb_row4 = new HBox(20);

        Button key1 = new Button("1");
        Button key2 = new Button("2");
        Button key3 = new Button("3");
        Button key4 = new Button("4");
        Button key5 = new Button("5");
        Button key6 = new Button("6");
        Button key7 = new Button("7");
        Button key8 = new Button("8");
        Button key9 = new Button("9");
        Button key0 = new Button("0");
        Button key_ok = new Button("ok");
        Button key_delete = new Button("x");

        Button[] keys = new Button[]{ key1, key2, key3, key4, key5, key6, key7, key8, key9, key_delete, key0, key_ok };

        kb_row1.getChildren().addAll(key1, key2, key3);
        kb_row2.getChildren().addAll(key4, key5, key6);
        kb_row3.getChildren().addAll(key7, key8, key9);
        kb_row4.getChildren().addAll(key_delete, key0, key_ok);

        keyboard.getChildren().addAll(kb_row1, kb_row2, kb_row3, kb_row4);
        for ( Button k : keys ) {
            k.getStyleClass().addAll("keypad_button");
        }

        // key functions
        key_ok.setOnAction( e -> {
            String s = textField.getText();
            if( s.length() == codeLength ) {
                onOkayMsg.setText("Verifying ...");
                onOkayMsg.setTextFill(Color.valueOf("#d6ffb2"));
                try{
                    if (codeChecker.checkValidity(s)){
                        onOkayMsg.setText("Valid!");
                        onOkayMsg.setTextFill(Color.valueOf("#d6ffb2"));
                        //TODO go to ok screen and open the door
                    } else {
                        onOkayMsg.setText("Invalid code, please retry!");
                        onOkayMsg.setTextFill(Color.valueOf("#ffc8cd"));}
                } catch (Exception t){
                    System.out.println(t);
                }

                // TODO on key_ok check code
            } else {
                onOkayMsg.setText("A "+codeLength+"-digit code is required.");
                onOkayMsg.setTextFill(Color.valueOf("#ffc8cd"));
            }
        });
        key_delete.setOnAction( e -> {
            onOkayMsg.setText("");
            textField.setText("");
        });

        key0.setOnAction( e -> {
            onOkayMsg.setText("");
            String s = textField.getText();
            textField.setText( s + "0" );
        });
        key1.setOnAction( e -> {
            onOkayMsg.setText("");
            String s = textField.getText();
            textField.setText( s + "1" );
        });
        key2.setOnAction( e -> {
            onOkayMsg.setText("");
            String s = textField.getText();
            textField.setText( s + "2" );
        });
        key3.setOnAction( e -> {
            onOkayMsg.setText("");
            String s = textField.getText();
            textField.setText( s + "3" );
        });
        key4.setOnAction( e -> {
            onOkayMsg.setText("");
            String s = textField.getText();
            textField.setText( s + "4" );
        });
        key5.setOnAction( e -> {
            onOkayMsg.setText("");
            String s = textField.getText();
            textField.setText( s + "5" );
        });
        key6.setOnAction( e -> {
            onOkayMsg.setText("");
            String s = textField.getText();
            textField.setText( s + "6" );
        });
        key7.setOnAction( e -> {
            onOkayMsg.setText("");
            String s = textField.getText();
            textField.setText( s + "7" );
        });
        key8.setOnAction( e -> {
            onOkayMsg.setText("");
            String s = textField.getText();
            textField.setText( s + "8" );
        });
        key9.setOnAction( e -> {
            onOkayMsg.setText("");
            String s = textField.getText();
            textField.setText( s + "9" );
        });

        return keyboard;
    }

    private void makeLayout(){
        // make components
        BorderPane layout = new BorderPane();
        VBox main = new VBox(20);
        VBox controls_back = new VBox(0);
        Button backBtn = new Button();
        VBox refine = new VBox();
        refine.setPrefWidth(50);

        Insets paddingStd = new Insets(20,20,20,20);

        //TODO put the strings as resource for translation later
        Label msg = new Label("Please enter the 4 digit code you find in your app.");

        // assemble components
        TextField textField = new TextField();
        textField.setAlignment(Pos.CENTER);
        addTextLimiter(textField, codeLength);
        HBox keyboardWrapper = new HBox(0);
        VBox keyboard = makeKeyboard(textField);
        keyboardWrapper.getChildren().addAll(keyboard);

        VBox inputWrapper = new VBox(40);
        inputWrapper.getChildren().addAll(textField, keyboardWrapper);
        inputWrapper.maxWidthProperty().bind( keyboardWrapper.widthProperty() );

        main.getChildren().addAll(msg, onOkayMsg, inputWrapper);
        main.setPadding(paddingStd);
        main.setAlignment(Pos.CENTER);

        msg.setAlignment(Pos.CENTER);
        msg.getStyleClass().addAll("h5");
        onOkayMsg.setAlignment(Pos.CENTER);
        onOkayMsg.getStyleClass().addAll("h5");

        keyboardWrapper.setMaxWidth(Double.MAX_VALUE);
        keyboardWrapper.setAlignment(Pos.CENTER);

        backBtn.setPrefWidth(50);
        backBtn.setPrefHeight(50);
        backBtn.getStyleClass().addAll("back_button");
        controls_back.getChildren().addAll(backBtn);

        layout.setCenter(main);
        layout.setLeft(controls_back);
        layout.setRight(refine);
        layout.getStyleClass().addAll("blueBG");

        scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("resources/Style.css").toString());
        this.setScene(scene);

        backBtn.setOnAction( e -> stageController.goHome());
    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener( e -> {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
        });
    }

    @Override
    public Scene getMyScene() {
        return scene;
    }
}
