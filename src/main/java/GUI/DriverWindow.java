package GUI;
import Malak_Khaled.Driver;
import Malak_Khaled.Trip;
import Malak_Khaled.verifyDriver;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class DriverWindow {
    public DriverWindow (){}
    public void DriverWindow(Driver driver) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("WELCOME TO YOUR ACCOUNT");
        window.setMinWidth(250.0D);
        List<Trip> s = verifyDriver.getDrivertrips(driver);

        ListView listView = new ListView();
            for (int i = 0; i < s.size(); i++) {
                String me = s.get(i).getSource();
                String me1 = s.get(i).getDes();
                int me3 = s.get(i).getStopsNum();
                String temp = "FROM:  " + me + "  TO:  " + me1 + " " + me3;
                listView.getItems().add(temp);
            }


            Label driverName = new Label("NAME:");
            Label driverWage = new Label("WAGE:");
            Label driverDOB = new Label("DATE OF BIRTH:");
            VBox driverInfo = new VBox();
            driverInfo.getChildren().addAll(driverName, driverWage, driverDOB, listView);
        }


    }
