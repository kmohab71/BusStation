package GUI;

import Malak_Khaled.User;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Date;

public class CarRent {
    private final String car ="https://img.etimg.com/thumb/msid-65768633,width-643,imgsize-251384,resizemode-4/lamborghinispyder.jpg";
    private final String car3 ="https://img.etimg.com/thumb/msid-67880792,width-643,imgsize-169790,resizemode-4/untitled-1.jpg";
    private final String car2 ="http://cdn.deeptem.com/car/wp-content/uploads/2018/04/post2-643x482.jpg";

    //private final String car2 ="https://cdn2.evo.co.uk/sites/evo/files/styles/gallery_adv/public/002-porsche-718-boxster_0.jpg?itok=CIleTPBP";
    //private final String car3 ="https://static.euronews.com/articles/stories/03/31/78/22/880x495_cmsv2_49ee514d-ffa0-53f8-94b3-6c4deb171498-3317822.jpg";
    public void rentingCars(User user){
        Stage carWindow = new Stage();
        carWindow.initModality(Modality.APPLICATION_MODAL);
        carWindow.setTitle("RENT A CAR");



        ImageView car1view = new ImageView(car);
        car1view.setX(50);
        car1view.setY(25);
        car1view.setFitHeight(150);
        car1view.setFitWidth(250);
        Button rent_the_car = new Button("RENT THIS CAR");
        rent_the_car.setOnAction(event -> {
            //Car one_car = new Car("pooh",new Date(12/1/1999),5);
            user.addCartoUser("pooh",new Date(12/1/1999),5);
        });



        ImageView car2view = new ImageView(car2);
        car2view.setX(50);
        car2view.setY(25);
        car2view.setFitHeight(150);
        car2view.setFitWidth(250);
        Button rent_the_car2 = new Button("RENT THIS CAR");
        rent_the_car2.setOnAction(event -> {
            //Car ot_car = new Car("pinky",new Date(9/7/1996),9);
            user.addCartoUser("pinky",new Date(9/7/1996),9);
        });



        ImageView car3view = new ImageView(car3);
        car3view.setX(50);
        car3view.setY(25);
        car3view.setFitHeight(150);
        car3view.setFitWidth(250);
        Button rent_the_car3 = new Button("RENT THIS CAR");
        rent_the_car3.setOnAction(event -> {
            //Car otp_car = new Car("funky",new Date(9/9/2000),9);
            user.addCartoUser("funky",new Date(9/9/2000),9);
        });




        carWindow.setMinWidth(250.0D);
        SplitPane carz = new SplitPane();
        carz.getItems().add(new VBox(car1view,rent_the_car));
        carz.getItems().add(new VBox(car2view,rent_the_car2));
        carz.getItems().add(new VBox(car3view,rent_the_car3));
        Scene carScene = new Scene(carz,860,600);
        carWindow.setScene(carScene);
        carWindow.show();

    }
}
