package GUI;
import Malak_Khaled.DB_config;
import Malak_Khaled.Trip;
import Malak_Khaled.User;
import dev.morphia.query.Query;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class book {
    public static Boolean isInternal;
    public static Boolean isOneWay;
    public static Boolean isEcon;

    public book(){}
    public static Boolean getIsEcon() {
        return isEcon;
    }

    public static void setIsEcon(Boolean isEcon) {
        book.isEcon = isEcon;
    }



    public static Boolean getIsInternal() {
        return isInternal;
    }

    public static void setIsInternal(Boolean isInternal) {
        book.isInternal = isInternal;
    }

    public static Boolean getIsOneWay() {
        return isOneWay;
    }

    public static void setIsOneWay(Boolean isOneWay) {
        book.isOneWay = isOneWay;
    }
    private static void getChoice(ChoiceBox<String> choiceBox){
        String food = choiceBox.getValue();
        System.out.println(food);
    }
    public static void booking(User user){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("BOOK YOUR TRIP");
        window.setMinWidth(250.0D);

        ToggleGroup radioGroup = new ToggleGroup();
        RadioButton internal = new RadioButton("INTERNAL    ");
        internal.setOnAction((e) -> {setIsInternal(true); });
        RadioButton external = new RadioButton("EXTERNAL    ");
        external.setOnAction((e) ->{setIsInternal(false) ;});

        internal.setToggleGroup(radioGroup);
        external.setToggleGroup(radioGroup);

        ToggleGroup radioGroup2 = new ToggleGroup();
        RadioButton oneway = new RadioButton("ONE WAY    ");
        oneway.setOnAction((e) -> {setIsOneWay(true); });
        RadioButton roundtrip = new RadioButton("ROUND TRIP    ");
        roundtrip.setOnAction((e) ->{setIsOneWay(false) ;});
        oneway.setToggleGroup(radioGroup2);
        roundtrip.setToggleGroup(radioGroup2);

        ToggleGroup radioGroup3 = new ToggleGroup();
        RadioButton economy = new RadioButton("ECONOMY    ");
        economy.setOnAction((e) -> {setIsEcon(true); });
        RadioButton firstClass = new RadioButton("FIRST CLASS    ");
        firstClass.setOnAction((e) ->{setIsEcon(false) ;});

        economy.setToggleGroup(radioGroup3);
        firstClass.setToggleGroup(radioGroup3);



        HBox checkboxes = new HBox(internal, external);
        HBox checkboxes2 = new HBox(oneway, roundtrip);
        HBox checkboxes3 = new HBox(economy, firstClass);


        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        ChoiceBox<String> choiceBox2 = new ChoiceBox<>();
        choiceBox.getItems().add("SOURCE");choiceBox2.getItems().add("DEStination");
        choiceBox.setValue("SOURCE");choiceBox2.setValue("DEStination");

        Query<Trip> tripsQueryList =  DB_config.datastore.createQuery(Trip.class);
        List<Trip> tripList = tripsQueryList.asList();
        for (int i =0 ; i< tripList.size();i++)
        {
            choiceBox.getItems().add(tripList.get(i).getSource());
            choiceBox2.getItems().add(tripList.get(i).getDes());
        }





        Button submit = new Button("SUBMIT");
        submit.setOnAction((e) -> {
            //window.close();
            String source =choiceBox.getValue();
            String destination = choiceBox2.getValue();

            //tripsQueryList22.filter(destination,"des");
            //List<Trip> tripList22 = tripsQueryList22.asList();
            //int x =tripList22.size();
            //System.out.println(x);


            Query<Trip> tripsQueryList22 =  DB_config.datastore.createQuery(Trip.class).field("source").equal(source).field("des").equal(destination);

            List<Trip> x = tripsQueryList22.asList();
            viewTripsChose me = new viewTripsChose();
            me.viewTripsChose(user,x,getIsOneWay(),getIsEcon());
/*
            for(int i=0;i<x.size();i++)
                {
                System.out.println(i+1+" "+x.get(i).source+" "+x.get(i).des+" "+x.get(i).price);
                }
            if (x.size()>=1)
                {
                verifyUser.user1.addTriptoUser(x.get(0),!getIsOneWay(),!getIsEcon());
                }

 */





            System.out.println(" is it internal" + getIsInternal() );
            System.out.println(" is it one way " + getIsOneWay() );
            System.out.println(" is it Economy " + getIsEcon() );
            //viewTripsChose yaraztripz = new viewTripsChose();
            //yaraztripz.viewTripsChose(temp);

        });






        VBox first = new VBox();
        first.getChildren().addAll(checkboxes2,checkboxes,checkboxes3,choiceBox,choiceBox2,submit);
        Scene firstpagee = new Scene(first, 500.0D, 200.0D);
        window.setTitle("BUS STATION");
        window.setScene(firstpagee);
        window.show();


    }
}
