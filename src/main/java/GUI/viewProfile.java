package GUI;

import Malak_Khaled.User;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class viewProfile {
    public viewProfile(){}
    public static void viewProfile(User user){

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("TRIPS");
        window.setMinWidth(250.0D);
        ListView listView = new ListView();
        for (int i = 0; i< user.getReserved().size(); i++){
            String me =  user.getReserved().get(i).getTrip().getSource();
            String me1 =  user.getReserved().get(i).getTrip().getDes();
            String temp = "FROM:  "+me+"  TO:  "+me1;
            listView.getItems().addAll(temp);

        }
        Button newbee= new Button("Do you what to delete this trip");
        newbee.setOnAction(event -> {

            ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();

            for(Object o : selectedIndices){
                String no = user.getReserved().get((Integer) o).getTrip().getSource();
                System.out.println(no);

            }
        });
        VBox hbox = new VBox(listView,newbee);
        hbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(hbox, 300, 120);
        window.setScene(scene);
        window.showAndWait();


    }
}
