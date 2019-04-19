package GUI;

import Malak_Khaled.Trip;
import Malak_Khaled.User;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class userWindow {
    public userWindow(){}
    public void userWindow(User user) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("TRIPS");
        window.setMinWidth(250.0D);

        TableView tableView = new TableView();

        TableColumn<String, Trip> column1 = new TableColumn<>("Source");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, Trip> column2 = new TableColumn<>("Destination");
        column2.setCellValueFactory(new PropertyValueFactory<>("Username"));

        TableColumn<String, Trip> column3 = new TableColumn<>("Date");
        //column2.setCellValueFactory(new PropertyValueFactory<>("Username"));



        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);

        for (int i = 0; i< user.getReserved().size(); i++)
            tableView.getItems().add(new User(user.getReserved().get((Integer) i).getTrip().getSource(), user.getReserved().get((Integer) i).getTrip().getDes(),"99"));



        Button newbee= new Button("Do you what to delete this trip");
        newbee.setOnAction(event -> {

            ObservableList selectedIndices = tableView.getSelectionModel().getSelectedIndices();

            for(Object o : selectedIndices){
                String no = user.getReserved().get((Integer) o).getTrip().getSource();
                System.out.println(no);

            }
        });

        VBox hbox = new VBox(tableView,newbee);
        hbox.setAlignment(Pos.CENTER);

        try {

            SplitPane split_pane = new SplitPane();
            Label name = new Label(user.getName());
            Button ROKA =new Button("BOOK A NEW TRIP");
            ROKA.setOnAction(e -> {
                //window.close();
                book booktripz = new book();
                booktripz.booking(user);});
            Button logout = new Button("LOG OUT");
            logout.setOnAction(e -> {
                GUI onez = new GUI(window);
                onez.GUI(window);

            });
            VBox ROKAb = new VBox();
            ROKAb.getChildren().addAll(name,ROKA,logout);
            split_pane.getItems().add(ROKAb);
            split_pane.getItems().add(hbox);

            // create a scene
            Scene scene = new Scene(split_pane, 500, 300);

            // set the scene
            window.setScene(scene);

            window.showAndWait();
        }

        catch (Exception e) {

            System.out.println(e.getMessage());
        }



    }
}
