package GUI;
import Malak_Khaled.DB_config;
import Malak_Khaled.Trip;
import Malak_Khaled.User;
import dev.morphia.query.Query;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.List;

public class book {
    private   Boolean isInternal;
    private   Boolean isOneWay;
    private   Boolean isEcon;

    public Boolean getInternal() {
        return isInternal;
    }

    public void setInternal(Boolean internal) {
        isInternal = internal;
    }

    public Boolean getOneWay() {
        return isOneWay;
    }

    public void setOneWay(Boolean oneWay) {
        isOneWay = oneWay;
    }

    public Boolean getEcon() {
        return isEcon;
    }

    public void setEcon(Boolean econ) {
        isEcon = econ;
    }

    public book(){}


    public  void booking(User user){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("BOOK YOUR TRIP");
        window.setMinWidth(250.0D);

        ToggleGroup radioGroup = new ToggleGroup();
        RadioButton internal = new RadioButton("INTERNAL    ");
        internal.setOnAction((e) -> {setInternal(true); });
        RadioButton external = new RadioButton("EXTERNAL    ");
        external.setOnAction((e) ->{setInternal(false) ;});

        internal.setToggleGroup(radioGroup);
        external.setToggleGroup(radioGroup);

        ToggleGroup radioGroup2 = new ToggleGroup();
        RadioButton oneway = new RadioButton("ONE WAY    ");
        oneway.setOnAction((e) -> {setOneWay(true); });
        RadioButton roundtrip = new RadioButton("ROUND TRIP    ");
        roundtrip.setOnAction((e) ->{setOneWay(false) ;});
        oneway.setToggleGroup(radioGroup2);
        roundtrip.setToggleGroup(radioGroup2);

        ToggleGroup radioGroup3 = new ToggleGroup();
        RadioButton economy = new RadioButton("ECONOMY    ");
        economy.setOnAction((e) -> {setEcon(true); });
        RadioButton firstClass = new RadioButton("FIRST CLASS    ");
        firstClass.setOnAction((e) ->{setEcon(false) ;});

        economy.setToggleGroup(radioGroup3);
        firstClass.setToggleGroup(radioGroup3);



        HBox checkboxes = new HBox(internal, external);
        HBox checkboxes2 = new HBox(oneway, roundtrip);
        HBox checkboxes3 = new HBox(economy, firstClass);


        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        ChoiceBox<String> choiceBox2 = new ChoiceBox<>();
        choiceBox.getItems().add("SOURCE");choiceBox2.getItems().add("DESTINATION");
        choiceBox.setValue("SOURCE");choiceBox2.setValue("DESTINATION");

        Query<Trip> tripsQueryList =  DB_config.datastore.createQuery(Trip.class);
        List<Trip> tripList = tripsQueryList.asList();
        for (int i =0 ; i< tripList.size();i++)
        {
            choiceBox.getItems().add(tripList.get(i).getSource());
            choiceBox2.getItems().add(tripList.get(i).getDes());
        }

        Button cancel = new Button("CANCEL");
        cancel.setOnAction(e -> {
            /*window.close();
            userWindow nextt = new userWindow();
            nextt.userWindow(user);

             */
        });

        DatePicker datePicker = new DatePicker();



        Button submit = new Button("SUBMIT");
        submit.setOnAction((e) -> {
            //window.close();
            Date value = Date.valueOf(datePicker.getValue());


            String source =choiceBox.getValue();
            String destination = choiceBox2.getValue();

            Query<Trip> tripsQueryList22 =  DB_config.datastore.createQuery(Trip.class).field("source").equal(source).field("des").equal(destination);

            List<Trip> x = tripsQueryList22.asList();



            ListView listView = new ListView();

            for (int i=0;i<x.size();i++)
            {
                String me2 =  x.get(i).getSource();
                String me1 =  x.get(i).getDes();
                int me3 = x.get(i).getStopsNum();
                String temp = "FROM:  "+me2+"  TO:  "+me1+" "+ me3;
                listView.getItems().add(temp);
            }

            Button button = new Button("BOOK THIS TRIP");

            button.setOnAction(event -> {

                ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();
                for(Object o : selectedIndices){

                    int no = x.get((Integer) o).getStopsNum();
                    System.out.println(no);

                    user.addTriptoUser(x.get(0),!getOneWay(),!getEcon());

                }

                window.close();
            });


            VBox vBox = new VBox(listView, button);

            Scene scene77 = new Scene(vBox, 300, 120);
            window.setScene(scene77);



        });






        VBox first0 = new VBox();
        first0.getChildren().addAll(checkboxes2,checkboxes,checkboxes3,choiceBox,choiceBox2,datePicker,submit,cancel);
        Scene bookpage = new Scene(first0, 500.0D, 200.0D, Color.AQUAMARINE);
        window.setTitle("BUS STATION");
        window.setScene(bookpage);
        window.show();


    }
}
