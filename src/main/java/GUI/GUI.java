package GUI;

import Malak_Khaled.verify;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI {
    public static int choice = 0;

    public Stage primaryStage;
    public GUI(){}
    public GUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public static void GUI(Stage primaryStage){

        Label welcome = new Label("WELCOME");
        TextField username = new TextField();


        PasswordField passwordField = new PasswordField();

        ToggleGroup radioGroup = new ToggleGroup();
        RadioButton user = new RadioButton("USER    ");
        user.setOnAction((e) -> {choice= 1; });
        RadioButton driver = new RadioButton("DRIVER    ");
        driver.setOnAction((e) ->{choice =2 ;});
        RadioButton manger = new RadioButton("MANGER    ");
        manger.setOnAction((e) -> {choice= 3;});

        user.setToggleGroup(radioGroup);
        driver.setToggleGroup(radioGroup);
        manger.setToggleGroup(radioGroup);
        Button done = new Button("DONE");
        done.setOnAction((e) -> {

            /*verifyUser passCheck = new verifyUser();
            User x = passCheck.verifyUser(username.getText(),passwordField.getText());
            if (x!=null) {*/
                verify entaMeenn = new verify();
                entaMeenn.entameen(choice,username.getText(),passwordField.getText());


        });


        HBox checkboxes = new HBox(user, driver, manger);
        VBox first = new VBox();
        first.getChildren().addAll(welcome,username,passwordField,checkboxes,done);
        Scene firstpagee = new Scene(first, 500.0D, 200.0D);
        primaryStage.setTitle("BUS STATION");
        primaryStage.setScene(firstpagee);
        primaryStage.show();



    }
}
