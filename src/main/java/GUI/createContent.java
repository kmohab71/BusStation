package GUI;

import Malak_Khaled.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class createContent {
    private  final String APPLICATION_ICON2 ="https://www.malaysia-expressbus.com/wp-content/uploads/2013/09/aa.png";
    private  final String APPLICATION_ICON ="https://www.malaysia-expressbus.com/wp-content/uploads/2013/09/aa.png";

    private  int choice = 0;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private ImageView img = new ImageView(new Image(APPLICATION_ICON2));
    private ImageView img2 = new ImageView(new Image(APPLICATION_ICON));
    private Stage primaryStage;
    private Scene firstscene;
    private Scene scene2;
    private Boolean isInternal;
    private Boolean isOneWay;
    private Boolean isEcon;


    public Scene getScene2() {
        return scene2;
    }

    public void setScene2(Scene scene2) {
        this.scene2 = scene2;
    }

    public Scene getFirstscene() {
        return firstscene;
    }

    public void setFirstscene(Scene firstscene) {
        this.firstscene = firstscene;
    }

    private  Pane root = new Pane();
    private  Pane root2 = new Pane();
    public  createContent(Stage primaryStage){
        this.primaryStage = primaryStage;
        createContent();
    }
    public createContent(){}

    private void createContent() {

        root.setPrefSize(860, 600);

        img.setFitWidth(860);
        img.setFitHeight(600);
        root.getChildren().add(img);


        TextField username2 = new TextField();username2.setMaxSize(0, 0);username2.setMinSize(0, 0);
        createContent.MenuItem itemExit = new createContent.MenuItem("EXIT");
        itemExit.setOnMouseClicked(e -> System.exit(0));
        createContent.MenuItem sumbit = new createContent.MenuItem("SUBMIT");
        createContent.MenuItem signup = new createContent.MenuItem("SIGN UP");
        signup.setOnMouseClicked(e ->{ ix();

        });
        createContent.MenuItem helpCenter = new createContent.MenuItem("HELP");

        // menu.setTranslateX(100);
        //menu.setTranslateY(300);

        ToggleGroup radioGroup = new ToggleGroup();
        RadioButton userB = new RadioButton("USER        ");
        userB.setFont(new Font("Arial", 12));
        userB.setOnAction((e) -> {
            choice = 1;
        });
        RadioButton driverB = new RadioButton("DRIVER        ");
        driverB.setFont(new Font("Arial", 12));
        driverB.setOnAction((e) -> {
            choice = 2;
        });
        RadioButton mangerB = new RadioButton("MANGER        ");
        mangerB.setFont(new Font("Arial", 12));
        mangerB.setOnAction((e) -> {
            choice = 3;
        });

        userB.setToggleGroup(radioGroup);
        driverB.setToggleGroup(radioGroup);
        mangerB.setToggleGroup(radioGroup);

        HBox checkboxes = new HBox(45,userB, driverB, mangerB);
        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        // root2.getChildren().addAll(checkboxes);
        sumbit.setOnMouseClicked(e-> ifx(username,passwordField));
        createContent.MenuBox2 menu = new createContent.MenuBox2(
                sumbit, itemExit);
        createContent.MenuBox2 menu2 = new createContent.MenuBox2(signup,helpCenter);
        VBox buttonz = new VBox(2);
        buttonz.getChildren().addAll(menu,menu2);

        VBox me = new VBox(10);
        me.getChildren().addAll(username2,username,passwordField,checkboxes,buttonz);
        me.setTranslateX(410);
        me.setTranslateY(170);
        root.getChildren().addAll(me);
        firstscene = new Scene(root);
        setFirstscene(firstscene);
        primaryStage.setScene(firstscene);
        primaryStage.show();
    }



    private GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Registration Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel0 = new Label("Name : ");
        gridPane.add(nameLabel0, 0,1);

        // Add Name Text Field
        TextField nameField0 = new TextField();
        nameField0.setPrefHeight(40);
        gridPane.add(nameField0, 1,1);


        // Add Email Label
        Label usernameLabel0 = new Label("Username : ");
        gridPane.add(usernameLabel0, 0, 2);

        // Add Email Text Field
        TextField usernameField0 = new TextField();
        usernameField0.setPrefHeight(40);
        gridPane.add(usernameField0, 1, 2);

        // Add Password Label
        Label passwordLabel0 = new Label("Password : ");
        gridPane.add(passwordLabel0, 0, 3);

        // Add Password Field
        PasswordField passwordField0 = new PasswordField();
        passwordField0.setPrefHeight(40);
        gridPane.add(passwordField0, 1, 3);

        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 4, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(nameField0.getText().isEmpty()) {
                    showAlert(javafx.scene.control.Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!", "Please enter your name");
                    return;
                }
                if(usernameField0.getText().isEmpty()) {
                    showAlert(javafx.scene.control.Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!", "Please enter your username");
                    return;
                }
                if(passwordField0.getText().isEmpty()) {
                    showAlert(javafx.scene.control.Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!", "Please enter a password");
                    return;
                }

                showAlert(javafx.scene.control.Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!", "Welcome " + nameField0.getText());
                Creator upup = new Creator();
                User user1= upup.createUser(nameField0.getText(),usernameField0.getText(),passwordField0.getText());
            }
        });
    }

    private void showAlert(javafx.scene.control.Alert.AlertType alertType, Window owner, String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    private void ix() {

        Stage sign_up = new Stage();
        sign_up.initModality(Modality.APPLICATION_MODAL);
        sign_up.setTitle("CREATE A NEW ACCOUNT");
        sign_up.setMinWidth(250);

        GridPane gridPane = createRegistrationFormPane();
        addUIControls(gridPane);
        Scene scene00 = new Scene(gridPane, 800, 500);
        sign_up.setScene(scene00);

        sign_up.show();

    }



    private void ifx(TextField username, PasswordField passwordField){

        if (choice==0){
            showAlert(javafx.scene.control.Alert.AlertType.CONFIRMATION, primaryStage, "ERROR","PLEASE SELECT ONE OF THE THREE RADIO BUTTONS");

        }
        if(choice==1){

            setUser(verifyUser.verifyUser(username.getText(),passwordField.getText()));
            if (user!=null){
                //root2.getChildren().add(img);
                SplitPane split_pane = new SplitPane();
                createContent.MenuItem name = new createContent.MenuItem(user.getName());
                createContent.MenuItem booktrip = new createContent.MenuItem("BOOK");
                booktrip.setOnMouseClicked(event ->{
                    //primaryStage.close();
                    book bookatrip = new book();
                    bookatrip.booking(user);

                });


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

                for (int i = 0; i< getUser().getReserved().size(); i++)
                    tableView.getItems().add(new User(getUser().getReserved().get((Integer) i).getTrip().getSource(), getUser().getReserved().get((Integer) i).getTrip().getDes(),getUser().getReserved().get((Integer) i).getTrip().getDate().toString()));

                createContent.MenuItem newbee = new createContent.MenuItem("DELETE TRIP");

                newbee.setOnMouseClicked(event -> {

                    ObservableList selectedIndices = tableView.getSelectionModel().getSelectedIndices();

                    for(Object o : selectedIndices){
                        getUser().DeleteTrip(getUser().getReserved().get((Integer) o).getTrip());
                        String no = getUser().getReserved().get((Integer) o).getTrip().getSource();
                        System.out.println(no);

                    }
                });
                VBox RIGHT = new VBox(tableView,newbee);
                RIGHT.setAlignment(Pos.CENTER);
                createContent.MenuItem logout = new createContent.MenuItem("LOGOUT");
                createContent.MenuItem refresh = new createContent.MenuItem("REFRESH");
                createContent.MenuItem rent_a_car = new createContent.MenuItem("RENT A CAR");
                createContent.MenuItem my_cars = new createContent.MenuItem("RENTED CARS");
                my_cars.setOnMouseClicked(event -> {
                    refreshB(username,passwordField);
                    System.out.println(user.getReservedCars().get(0).getName());
                });
                rent_a_car.setOnMouseClicked(event -> {

                    CarRent renting = new CarRent();
                    renting.rentingCars(user);
                });
                refresh.setOnMouseClicked(event -> {
                    refreshB(username,passwordField);
                    tableView.getItems().clear();
                    for (int i = 0; i< getUser().getReserved().size(); i++)
                        tableView.getItems().add(new User(getUser().getReserved().get((Integer) i).getTrip().getSource(), getUser().getReserved().get((Integer) i).getTrip().getDes(),getUser().getReserved().get((Integer) i).getTrip().getDate().toString()));



                });
                VBox LEFT0 =new VBox(6);
                LEFT0.getChildren().addAll(name,booktrip,logout,refresh);

                logout.setOnMouseClicked(event -> { primaryStage.setTitle("SUPP");createContent me = new createContent(primaryStage); });
                createContent.MenuBox LEFT = new createContent.MenuBox(
                        name,
                        booktrip,
                        logout,
                        rent_a_car,
                        my_cars,
                        refresh);


                split_pane.getItems().addAll(LEFT,RIGHT);
                split_pane.setStyle(
                        "-fx-padding: 5; " +
                                "-fx-background-color: grey; " +
                                "-fx-border-width:5; " +
                                "-fx-border-color: " +
                                "linear-gradient(" +
                                "to bottom, " +
                                "black, " +
                                "derive(black, 50%)" +
                                ");"
                );

                root2.getChildren().addAll(split_pane);
                //root2.setPrefSize(860, 600);
                scene2 = new Scene(root2,860,600);
                setScene2(scene2);
                primaryStage.setScene(scene2);
            }
            else
                showAlert(javafx.scene.control.Alert.AlertType.CONFIRMATION, primaryStage, "ERROR","YOU ARE NOT A REGISTERED USER");

        }
        if(choice==2){
            Driver driver = verifyDriver.getDriver(username.getText(),passwordField.getText());
            if (driver!=null)
            {
                primaryStage.close();
                DriverWindow next2 = new DriverWindow();
                next2.DriverWindow(driver);
            }
            else
                showAlert(javafx.scene.control.Alert.AlertType.CONFIRMATION, primaryStage, "ERROR","YOU ARE NOT A REGISTERED DRIVER");

        }
        if(choice==3){manger manger =verifyManger.verifyManger(username.getText(),passwordField.getText());
            if (manger!=null){
                primaryStage.close();
                MangerWindow next3 = new MangerWindow();
                next3.MangerWindowz(manger);
            }
            else
                showAlert(javafx.scene.control.Alert.AlertType.CONFIRMATION, primaryStage, "ERROR","YOU ARE NOT A REGISTERED MANGER");

        }
    }

    private void refreshB(TextField username, PasswordField passwordField) {
        setUser(verifyUser.verifyUser(username.getText(),passwordField.getText()));

    }


    private static class MenuBox extends VBox {
        public MenuBox(MenuItem... items) {
            getChildren().add(createSeparator());

            for (MenuItem item : items) {
                getChildren().addAll(item, createSeparator());
            }
        }

        private Line createSeparator() {
            Line sep = new Line();
            sep.setEndX(0);
            sep.setStroke(Color.DARKGREY);
            return sep;
        }
    }
    private static class MenuBox2 extends HBox {
        public MenuBox2(MenuItem... items) {
            getChildren().add(createSeparator());

            for (MenuItem item : items) {
                getChildren().addAll(item, createSeparator());
            }
        }

        private Line createSeparator() {
            Line sep = new Line();
            sep.setEndX(0);
            sep.setStroke(Color.DARKGREY);
            return sep;
        }
    }

    private static class MenuItem extends StackPane {
        private MenuItem(String name) {
            LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                    new Stop(0, Color.DARKBLUE),
                    new Stop(0.1, Color.BLACK),
                    new Stop(0.9, Color.BLACK),
                    new Stop(1, Color.DARKRED)
            });

            Rectangle bg = new Rectangle(200, 30);
            bg.setOpacity(0.4);

            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 22));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                bg.setFill(gradient);
                text.setFill(Color.WHITE);
            });


            setOnMouseExited(event -> {
                bg.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
            });

            setOnMousePressed(event -> {
                bg.setFill(Color.DARKRED);
            });

            setOnMouseReleased(event -> {
                bg.setFill(gradient);
            });
        }
    }
}
