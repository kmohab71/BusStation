package GUI;

import Malak_Khaled.Trip;
import Malak_Khaled.User;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class viewTripsChose {
    public viewTripsChose(){}

    public static void viewTripsChose(User user){
        //lessa
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("CHOOSE TRIPS");
        window.setMinWidth(250.0D);
        ListView listView = new ListView();

        for (int i = 0; i< user.getReserved().size(); i++)
            listView.getItems().add(user.getReserved().get(i).getTrip().getDes());

        Button button = new Button("Read Selected Value");

        button.setOnAction(event -> {

            ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();

            for(Object o : selectedIndices){
                System.out.println("o = " + o + " (" + o.getClass() + ")");
            }
        });


        VBox vBox = new VBox(listView, button);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox, 300, 120);
        window.setScene(scene);
        window.showAndWait();

    }
    public static void viewTripsChose(User user, List<Trip> buyTripList, Boolean isoneway, Boolean isecon){

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("CHOOSE TRIPS");
        window.setMinWidth(250.0D);
        ListView listView = new ListView();

        for (int i=0;i<buyTripList.size();i++)
        {
            String me =  buyTripList.get(i).getSource();
            String me1 =  buyTripList.get(i).getDes();
            int me3 = buyTripList.get(i).getStopsNum();
            String temp = "FROM:  "+me+"  TO:  "+me1+" "+ me3;
            listView.getItems().add(temp);
        }

        Button button = new Button("BOOK THIS TRIP");

        button.setOnAction(event -> {

            ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();
            for(Object o : selectedIndices){

                int no = buyTripList.get((Integer) o).getStopsNum();
                System.out.println(no);
                // prints the right info ->>> of the selected trip
                user.addTriptoUser(buyTripList.get(0),!isoneway,!isecon);

            }
        });


        VBox vBox = new VBox(listView, button);

        Scene scene = new Scene(vBox, 300, 120);
        window.setScene(scene);
        window.showAndWait();

    }

}
