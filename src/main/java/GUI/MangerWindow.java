package GUI;

import Malak_Khaled.Trip;
import Malak_Khaled.manger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class MangerWindow {
    private TableView<Product> table;
    public TextField sourceInput, destinationInput, stopsInput;
    public void MangerWindowz(manger manger) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("MANGER PROFILE");
        window.setMinWidth(250.0D);

        TableColumn<Product, String> nameColumn = new TableColumn<>("SOURCE");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Price column
        TableColumn<Product, Double> priceColumn = new TableColumn<>("DESTINATION");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Quantity column
        TableColumn<Product, String> quantityColumn = new TableColumn<>("NUMBER OF STOPS");
        quantityColumn.setMinWidth(200);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        //Name input
        sourceInput = new TextField();
        sourceInput.setPromptText("SOURCE");
        sourceInput.setMinWidth(100);

        //Price input
        destinationInput = new TextField();
        destinationInput.setPromptText("DESTINATION");

        //Quantity input
        stopsInput = new TextField();
        stopsInput.setPromptText("Quantity");

        //Button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked(manger));
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked(manger));

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(sourceInput, destinationInput, stopsInput, addButton, deleteButton);

        table = new TableView<>();
        table.setItems(getProduct(manger));
        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);

        Scene scene = new Scene(vBox);

        window.setScene(scene);
        window.showAndWait();
    }

    //Add button clicked
    public void addButtonClicked(manger manger){
        manger.addTrip(sourceInput.getText(),destinationInput.getText(),false,2,30,Integer.parseInt(stopsInput.getText()),40,"NOUR",29,5,2019);
        table.getItems().add(new Product(sourceInput.getText(),destinationInput.getText(),Integer.parseInt(stopsInput.getText())));
        sourceInput.clear();
        destinationInput.clear();
        stopsInput.clear();
        /* Product product = new Product();
        product.setName(sourceInput.getText());
        product.setPrice(destinationInput.getText());
        product.setQuantity(Integer.parseInt(stopsInput.getText()));
        table.getItems().add(product);
        sourceInput.clear();
        destinationInput.clear();
        stopsInput.clear();

        */
    }

    //Delete button clicked
    public void deleteButtonClicked(manger manger){

        Product productSelected2;
        ObservableList<Product> allProducts,productSelected;
        allProducts = table.getItems();
        productSelected2 = table.getSelectionModel().getSelectedItem();
        manger.deleteTrip(productSelected2.getName(),productSelected2.getPrice());
        productSelected = table.getSelectionModel().getSelectedItems();
        productSelected.forEach(allProducts::remove);



    }

    //Get all of the products
    public ObservableList<Product> getProduct(manger manger){
        ObservableList<Product> products = FXCollections.observableArrayList();
        List<Trip> exxx = manger.ViewTrips();
        for (int i =0; i<exxx.size();i++)
            products.add(new Product(exxx.get(i).getSource(), exxx.get(i).getDes(), exxx.get(i).getStopsNum()));

        return products;
    }


}