package GUI;
import Malak_Khaled.Driver;
import Malak_Khaled.Trip;
import Malak_Khaled.User;
import Malak_Khaled.verifyDriver;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

        TableView tableView = new TableView();

        TableColumn<String, Trip> column1 = new TableColumn<>("Source");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, Trip> column2 = new TableColumn<>("Destination");
        column2.setCellValueFactory(new PropertyValueFactory<>("Username"));

        TableColumn<String, Trip> column3 = new TableColumn<>("Date");
        column3.setCellValueFactory(new PropertyValueFactory<>("Password"));



        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);

        for (int i = 0; i< s.size(); i++)
            tableView.getItems().add(new User(s.get(i).getSource(),s.get(i).getDes(),s.get(i).getDate().toString()));
            //tableView.getItems().add(new User(user.getReserved().get((Integer) i).getTrip().getSource(), user.getReserved().get((Integer) i).getTrip().getDes(),"99"));



            Label driverName = new Label("NAME: " + driver.getName());
            Label driverWage = new Label("WAGE: " + driver.getSalary());
            Label driverDOB = new Label("");
            VBox driverInfo = new VBox();
            driverInfo.getChildren().addAll(driverName, driverWage, driverDOB, tableView);

            Scene no = new Scene(driverInfo,500,700, Color.CHOCOLATE);
            window.setScene(no);
            window.show();
        }


    }
