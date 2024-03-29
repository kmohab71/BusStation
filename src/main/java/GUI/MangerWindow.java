package GUI;

import Malak_Khaled.Trip;
import Malak_Khaled.manger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class MangerWindow {
    private TableView<Product> table;

    public Boolean getInternal() {
        return isInternal;
    }

    public void setInternal(Boolean internal) {
        isInternal = internal;
    }
    private int vehical_type = 0;

    public int getVehical_type() {
        return vehical_type;
    }

    public void setVehical_type(int vehical_type) {
        this.vehical_type = vehical_type;
    }

    private   Boolean isInternal;
    private DatePicker datePickerM;
    private LocalDate value;
    public TextField sourceInput, destinationInput, stopsInput,priceInput, DriverName,seatsAv;
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
        stopsInput.setPromptText("STOPS");

        priceInput = new TextField();
        priceInput.setPromptText("PRICE");
        priceInput.setMinWidth(100);

        DriverName = new TextField();
        DriverName.setPromptText("DRIVER");
        DriverName.setMinWidth(100);

        seatsAv = new TextField();
        seatsAv.setPromptText("SEATS");
        seatsAv.setMinWidth(100);
        datePickerM = new DatePicker();
        datePickerM.setOnAction(event -> {
            value = datePickerM.getValue();
            //System.out.println(value.getDayOfMonth());
        });

        //Button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked(manger));
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked(manger));
        Button out = new Button("Log Out");
        out.setOnAction(e -> {
            window.close();
            createContent lol = new createContent(window);
        });

        ToggleGroup newToggle = new ToggleGroup();
        RadioButton inter = new RadioButton("INTERNAL  ");
        inter.setOnAction(e ->{
            setInternal(true);
        });
        RadioButton external = new RadioButton("EXTERNAL  ");
        external.setOnAction(e ->{
            setInternal(false);
        });
        inter.setToggleGroup(newToggle);
        external.setToggleGroup(newToggle);
        HBox checkboxes = new HBox(inter,external);

        ToggleGroup newToggle22 = new ToggleGroup();
        RadioButton BUS = new RadioButton("BUS  ");
        BUS.setOnAction(e ->{
            setVehical_type(1);
        });
        RadioButton MINI = new RadioButton("MINI BUS  ");
        MINI.setOnAction(e ->{
            setVehical_type(2);
        });
        BUS.setToggleGroup(newToggle22);
        MINI.setToggleGroup(newToggle22);
        HBox checkboxes22 = new HBox(BUS,MINI);

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(sourceInput, destinationInput, stopsInput, addButton, deleteButton,out);

        HBox hBox22 = new HBox();
        hBox22.setPadding(new Insets(10,10,10,10));
        hBox22.setSpacing(10);
        hBox22.getChildren().addAll(checkboxes,priceInput,DriverName,datePickerM,checkboxes22);


        table = new TableView<>();
        table.setItems(getProduct(manger));
        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table,hBox, hBox22);

        Scene scene = new Scene(vBox);

        window.setScene(scene);
        window.showAndWait();
    }

    //Add button clicked
    public void addButtonClicked(manger manger){
        int spotsAv ;
        if (getVehical_type()==1){
            spotsAv=30;
        }
        else spotsAv=20;
        manger.addTrip(sourceInput.getText(),destinationInput.getText(),getInternal(),false,getVehical_type(),spotsAv,Integer.parseInt(stopsInput.getText()), Integer.parseInt(priceInput.getText()),DriverName.getText(),value.getDayOfMonth(),value.getMonthValue(),value.getYear());
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