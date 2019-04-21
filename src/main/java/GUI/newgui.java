package GUI;

import Malak_Khaled.DB_config;
import Malak_Khaled.verify;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


/**
 * Example of displaying a splash page for a standalone JavaFX application
 */
public class newgui extends Application {
    public static final String APPLICATION_ICON =
            "http://cdn1.iconfinder.com/data/icons/Copenhagen/PNG/32/people.png";
    public static final String SPLASH_IMAGE ="https://png.pngtree.com/thumb_back/fw800/back_pic/05/13/16/47599da38bcd6d2.jpg";
    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private Stage mainStage;
    private static final int SPLASH_WIDTH = 676;
    private static final int SPLASH_HEIGHT = 227;
    private int choice = 0;

    public static void main(String[] args) throws Exception {

        new DB_config();
        launch(args);

    }

    @Override
    public void init() {
        ImageView splash = new ImageView(new Image(
                SPLASH_IMAGE
        ));
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
        progressText = new Label("LOADING . . .");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        splashLayout.setStyle(
                "-fx-padding: 5; " +
                        "-fx-background-color: cornsilk; " +
                        "-fx-border-width:5; " +
                        "-fx-border-color: " +
                        "linear-gradient(" +
                        "to bottom, " +
                        "chocolate, " +
                        "derive(chocolate, 50%)" +
                        ");"
        );
        splashLayout.setEffect(new DropShadow());
    }

    @Override
    public void start(final Stage initStage) throws Exception {
        final Task<ObservableList<String>> friendTask = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws InterruptedException {
                ObservableList<String> foundFriends =
                        FXCollections.<String>observableArrayList();
                ObservableList<String> availableFriends =
                        FXCollections.observableArrayList(

                                "User info", "Driver info", "Trip details", "Database", "Database",
                                "Database", "Database", "Database", "Database",
                                "Database", "Database", "Database", "Database"
                        );

                updateMessage("LOADING . . .");
                for (int i = 0; i < availableFriends.size(); i++) {
                    Thread.sleep(400);
                    updateProgress(i + 1, availableFriends.size());
                    String nextFriend = availableFriends.get(i);
                    foundFriends.add(nextFriend);
                    updateMessage("LOADING . . .  " + nextFriend);
                }
                Thread.sleep(400);
                updateMessage("DONE");

                return foundFriends;
            }
        };

        showSplash(
                initStage,
                friendTask,
                () -> showMainStage(friendTask.valueProperty())
        );
        new Thread(friendTask).start();
    }

    private void showMainStage(
            ReadOnlyObjectProperty<ObservableList<String>> friends
    ) {
        mainStage = new Stage(StageStyle.DECORATED);
        mainStage.setTitle("BUS STATION");
        mainStage.getIcons().add(new Image(
                APPLICATION_ICON
        ));

        Label welcome = new Label("WELCOME");
        welcome.setFont(new Font("Arial", 35));
        TextField username2 = new TextField();
        username2.setMaxSize(0, 0);
        username2.setMinSize(0, 0);
        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        ToggleGroup radioGroup = new ToggleGroup();
        RadioButton user = new RadioButton("USER        ");
        user.setFont(new Font("Arial", 12));
        user.setOnAction((e) -> {
            choice = 1;
        });
        RadioButton driver = new RadioButton("DRIVER        ");
        driver.setFont(new Font("Arial", 12));
        driver.setOnAction((e) -> {
            choice = 2;
        });
        RadioButton manger = new RadioButton("MANGER        ");
        manger.setFont(new Font("Arial", 12));
        manger.setOnAction((e) -> {
            choice = 3;
        });

        user.setToggleGroup(radioGroup);
        driver.setToggleGroup(radioGroup);
        manger.setToggleGroup(radioGroup);
        Button done = new Button("DONE");
        done.setFont(new Font("American Typewriter Condensed", 23));
        done.setOnAction((e) -> {


            verify entaMeenn = new verify();
            entaMeenn.entameen(choice,username.getText(),passwordField.getText(),mainStage);


        });


        HBox checkboxes = new HBox(user, driver, manger);

        VBox first = new VBox(10);
        first.getChildren().addAll(welcome, username2, username, passwordField, checkboxes, done);
        Scene newmee = new Scene(first, 700, 500,Color.BURLYWOOD);
        mainStage.setScene(newmee);
        mainStage.show();
    }

    private void showSplash(
            final Stage initStage,
            Task<?> task,
            InitCompletionHandler initCompletionHandler
    ) {
        progressText.textProperty().bind(task.messageProperty());
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                loadProgress.progressProperty().unbind();
                loadProgress.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();

                initCompletionHandler.complete();
            }
        });

        Scene splashScene = new Scene(splashLayout, Color.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.initStyle(StageStyle.TRANSPARENT);
        initStage.setAlwaysOnTop(true);
        initStage.show();
    }

    public interface InitCompletionHandler {
        void complete();
    }
}