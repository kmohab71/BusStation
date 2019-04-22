package GUI;

import Malak_Khaled.*;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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


        TextField username2 = new TextField();
        username2.setMaxSize(0, 0);
        username2.setMinSize(0, 0);
        createContent.MenuItem itemExit = new createContent.MenuItem("EXIT");
        itemExit.setOnMouseClicked(e -> System.exit(0));
        createContent.MenuItem sumbit = new createContent.MenuItem("SUBMIT");
        createContent.MenuItem signup = new createContent.MenuItem("SIGN UP");
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
        sumbit.setOnMouseClicked(e->{
            if (choice==0){
                Alert.display("ERROR","PLEASE SELECT ONE OF THE THREE RADIO BUTTONS");
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
                    //column2.setCellValueFactory(new PropertyValueFactory<>("Username"));



                    tableView.getColumns().add(column1);
                    tableView.getColumns().add(column2);
                    tableView.getColumns().add(column3);

                    for (int i = 0; i< getUser().getReserved().size(); i++)
                        tableView.getItems().add(new User(getUser().getReserved().get((Integer) i).getTrip().getSource(), getUser().getReserved().get((Integer) i).getTrip().getDes(),"99"));

                    createContent.MenuItem newbee = new createContent.MenuItem("DELETE TRIP");

                    newbee.setOnMouseClicked(event -> {

                        ObservableList selectedIndices = tableView.getSelectionModel().getSelectedIndices();

                        for(Object o : selectedIndices){
                            String no = getUser().getReserved().get((Integer) o).getTrip().getSource();
                            System.out.println(no);

                        }
                    });
                    VBox RIGHT = new VBox(tableView,newbee);
                    RIGHT.setAlignment(Pos.CENTER);
                    createContent.MenuItem logout = new createContent.MenuItem("LOGOUT");
                    createContent.MenuItem refresh = new createContent.MenuItem("REFRESH");
                    createContent.MenuItem rent_a_car = new createContent.MenuItem("RENT A CAR");
                    rent_a_car.setOnMouseClicked(event -> {
                        CarRent renting = new CarRent();
                        renting.rentingCars(user);
                    });
                    refresh.setOnMouseClicked(event -> {
                        setUser(verifyUser.verifyUser(username.getText(),passwordField.getText()));
                        tableView.getItems().clear();
                        for (int i = 0; i< getUser().getReserved().size(); i++)
                            tableView.getItems().add(new User(getUser().getReserved().get((Integer) i).getTrip().getSource(), getUser().getReserved().get((Integer) i).getTrip().getDes(),"99"));



                    });
                    VBox LEFT0 =new VBox(6);
                    LEFT0.getChildren().addAll(name,booktrip,logout,refresh);

                    logout.setOnMouseClicked(event -> { primaryStage.setTitle("SUPP");createContent me = new createContent(primaryStage); });
                    createContent.MenuBox LEFT = new createContent.MenuBox(
                            name,
                            booktrip,
                            logout,
                            rent_a_car,
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
                    Alert.display("ERROR","YOU ARE NOT A REGISTERED USER");

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
                    Alert.display("ERROR","YOU ARE NOT A REGISTERED DRIVER");}
            if(choice==3){manger manger =verifyManger.verifyManger(username.getText(),passwordField.getText());
                if (manger!=null){
                    primaryStage.close();
                    MangerWindow next3 = new MangerWindow();
                    next3.MangerWindowz(manger);
                }
                else
                    Alert.display("ERROR","YOU ARE NOT A REGISTERED MANGER");}
        });
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
