import javafx.stage.Stage;

public interface StageController {
    void goHome();
    void goTo(String targetScene);
    void checkState();
    void goBack();
}
