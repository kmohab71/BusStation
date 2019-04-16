package GUI;

import Malak_Khaled.User;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class userWindow {
    public userWindow(){}
    public static void userWindow(User user) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("USER PROFILE");
        window.setMinWidth(250.0D);
        Label name = new Label(user.getName());

        Button viewProfile = new Button("VIEW PROFILE");
        viewProfile.setOnAction(e -> {

            viewProfile viewList = new viewProfile();
            viewList.viewProfile(user);
            //window.close();

        });
        Button book = new Button("BOOK TRIP");
        book.setOnAction(e -> {

            book booktrip = new book();
            booktrip.booking(user);

        });


        VBox signinorup = new VBox(9.0D);
        signinorup.getChildren().addAll(new Node[]{name,viewProfile,book});
        signinorup.setAlignment(Pos.CENTER);
        VBox layoutAll = new VBox(15.0D);
        layoutAll.getChildren().addAll(new Node[]{signinorup});
        Scene scene = new Scene(layoutAll);
        window.setScene(scene);
        window.showAndWait();
    }
}
