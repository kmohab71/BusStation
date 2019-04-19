package GUI;
import Malak_Khaled.verify;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI {
    private int choice = 0;



    private Stage primaryStage;
    public GUI(){}
    public GUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void GUI(Stage primaryStage){


        Label welcome = new Label("WELCOME");
        welcome.setFont(new Font("Arial",35));
        TextField username2 = new TextField();
        username2.setMaxSize(0,0);
        username2.setMinSize(0,0);
        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        ToggleGroup radioGroup = new ToggleGroup();
        RadioButton user = new RadioButton("USER        ");
        user.setFont(new Font("Arial",12));
        user.setOnAction((e) -> {choice= 1; });
        RadioButton driver = new RadioButton("DRIVER        ");
        driver.setFont(new Font("Arial",12));
        driver.setOnAction((e) ->{choice =2 ;});
        RadioButton manger = new RadioButton("MANGER        ");
        manger.setFont(new Font("Arial",12));
        manger.setOnAction((e) -> {choice= 3;});

        user.setToggleGroup(radioGroup);
        driver.setToggleGroup(radioGroup);
        manger.setToggleGroup(radioGroup);
        Button done = new Button("DONE");
        done.setFont(new Font("American Typewriter Condensed",23));
        done.setOnAction((e) -> {


                verify entaMeenn = new verify();
                entaMeenn.entameen(choice,username.getText(),passwordField.getText(),primaryStage);


        });



        HBox checkboxes = new HBox(user, driver, manger);

        VBox first = new VBox(10);
        first.getChildren().addAll(welcome,username2 ,username,passwordField,checkboxes,done);
        Scene firstpagee = new Scene(first, 500.0D, 500.0D);
        primaryStage.setTitle("BUS STATION");
        primaryStage.setScene(firstpagee);
        primaryStage.show();



    }
}


