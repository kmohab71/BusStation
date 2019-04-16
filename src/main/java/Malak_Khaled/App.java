package Malak_Khaled;

import GUI.GUI;
import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * Hello world!
 *
 */
public class App extends Application
{

    public void start(Stage primaryStage) throws Exception{
    GUI one = new GUI(primaryStage);
    one.GUI(primaryStage);
    }

    public static void main( String[] args )
    {
        final Morphia morphia = new Morphia();

        // tell morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.mapPackage("dev.morphia.example");

        // create the Datastore connecting to the database running on the default port on the local host
        final Datastore datastore = morphia.createDatastore(new MongoClient(), "Bus_station_collection");
        datastore.getDB().dropDatabase();
        datastore.ensureIndexes();
        new DB_config(datastore);
        //creator is the class responsible for creating users and mangers
        final Creator creator=new Creator();
        final User user1= creator.createUser("dsfa","abc","123");
        final User user2= creator.createUser("MALAK","","");
        final manger elmer =creator.createManger("Elmer Fudd", 50000, "elmer","1234");
        Trip trip[] = new Trip[9];
        //manger can add driver and trips and delete trips
        Driver banana = elmer.addDriver( 3000,"banana", "ban", "ana");
        trip[0]=elmer.addTrip("Mozambeek","Alexandria",false,1,30,800,800,"banana");
        trip[1]=elmer.addTrip("Paris","Barcelona",false,2,30,1,600,"banana");
        trip[2]=elmer.addTrip("italy","cuba",false,2,20,0,800,"banana");
        //trip[3]=elmer.addTrip("Istanbul","Beirut",false,2,true,600,"banana");
        //trip[4]=elmer.addTrip("Mozambeek","Alexandria",false,22,true,800,"banana");
        //trip[5]=elmer.addTrip("Mozambeek","Alexandria",false,33,true,800,"banana");
        //user can reserve trips
        System.out.println("driver list : "+banana.ViewTripDriver().get(0).getSource());
        user1.addTriptoUser(trip[0],true,true);
        user2.addTriptoUser(trip[2],true,true);
        user1.addTriptoUser(trip[1],true,true);
        //user2.addTriptoUser(trip[3],true,true);
        System.out.println(elmer.ViewTrips());
        //banana.ViewTripDriver(banana,datastore);
        System.out.println(user1.getinfo().getReserved());
        //elmer.deleteTrip(datastore,trip[0]);
        launch(args);

    }
}
