package Malak_Khaled;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

public class DB_config {
    public static Datastore datastore;
    public DB_config() {
        final Morphia morphia = new Morphia();
        morphia.mapPackage("Bus Station");
        final Datastore datastore = morphia.createDatastore(new MongoClient(new MongoClientURI("mongodb://localhost:27017")), "Test");
        datastore.ensureIndexes();
        this.datastore=datastore;
    }

}
