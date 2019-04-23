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
import javafx.stage.Window;

import java.util.List;

public class book {
    private   Boolean isInternal;
    private   Boolean isOneWay;
    private   Boolean isEcon;
    private String s= "Choose destination";

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


        choiceBox.setOnAction(e ->{
            s = choiceBox.getValue();
            choiceBox2.getItems().clear();
            choiceBox2.getItems().add("DESTINATION");
            choiceBox2.setValue("DESTINATION");

            Query<Trip> tripsQueryList232 =  DB_config.datastore.createQuery(Trip.class).field("source").equal(s);
            List<Trip> tr = tripsQueryList232.asList();
            for (int j =0 ; j< tr.size();j++) {

                choiceBox2.getItems().add(tr.get(j).getDes());

            }
        });







        Button submit = new Button("SUBMIT");
        submit.setOnAction((e) -> {
            //window.close();
            //Date value = Date.valueOf(datePicker.getValue());


            String source =choiceBox.getValue();
            String destination = choiceBox2.getValue();

            Query<Trip> tripsQueryList22 =  DB_config.datastore.createQuery(Trip.class).field("source").equal(source).field("des").equal(destination);

            List<Trip> x = tripsQueryList22.asList();



            ListView listView = new ListView();

            for (int i=0;i<x.size();i++)
            {
                String me2 =  x.get(i).getSource();
                String me1 =  x.get(i).getDes();
                int me3 = x.get(i).getDate().getDay()+1;
                int me4 = x.get(i).getDate().getMonth();
                int me5 = x.get(i).getDate().getYear();
                int numberP = x.get(i).getNumPeopleLeft();
                int price = x.get(i).getPrice();
                float Prices; int twoways=1;
                if(isOneWay) twoways=0;
                if (isEcon==false && !getInternal()) {
                    Prices= (float) (price*2-(twoways*price*0.2));

                }else if(isEcon==true  && !getInternal()){
                    Prices= (float) (price*1.75-(twoways*price*0.2));

                }else if(isEcon==false  && getInternal()){

                    Prices= (float) (price*1.5-(twoways*price*0.2));
                }else{
                    Prices= (float) (price*1.25-(twoways*price*0.4));
                }
                String temp = "FROM:  "+me2+"  TO:  "+me1+" "+ me3+"/"+me4+"/"+me5 +" PRICE:"+Prices+"  "+numberP;
                listView.getItems().add(temp);
            }

            Button button = new Button("BOOK THIS TRIP");

            button.setOnAction(event -> {
                Alert alert = new Alert();
                ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();
                for(Object o : selectedIndices){

                    int no = x.get((Integer) o).getNumPeopleLeft();
                    if (no==0){

                        alert.display( "Error!", "NO AVAILABLE SEATS");
                    }
                    else
                    {
                        user.addTriptoUser(x.get(0),!getOneWay(),!getEcon());
                        alert.display(  "DONE!", "SEAT RESERVED");
                    }


                }

                window.close();
            });


            VBox vBox = new VBox(listView, button);

            Scene scene77 = new Scene(vBox, 300, 120);
            window.setScene(scene77);



        });






        VBox first0 = new VBox();
        first0.getChildren().addAll(checkboxes2,checkboxes,checkboxes3,choiceBox,choiceBox2,submit);
        Scene bookpage = new Scene(first0, 500.0D, 200.0D, Color.AQUAMARINE);
        window.setTitle("BUS STATION");
        window.setScene(bookpage);
        window.show();


    }
    private void showAlert(javafx.scene.control.Alert.AlertType alertType, Window owner, String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
