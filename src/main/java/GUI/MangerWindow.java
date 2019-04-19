package GUI;

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
        addButton.setOnAction(e -> addButtonClicked());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(sourceInput, destinationInput, stopsInput, addButton, deleteButton);

        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);

        Scene scene = new Scene(vBox);

        window.setScene(scene);
        window.showAndWait();
    }

    //Add button clicked
    public void addButtonClicked(){
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
    public void deleteButtonClicked(){
        ObservableList<Product> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();

        productSelected.forEach(allProducts::remove);
    }

    //Get all of the products
    public ObservableList<Product> getProduct(){
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product("Laptop", "pp", 20));
        products.add(new Product("Bouncy Ball", "pp", 198));
        products.add(new Product("Toilet", "pp", 74));
        products.add(new Product("The Notebook DVD", "pp", 12));
        products.add(new Product("Corn", "tt", 856));
        return products;
    }


}