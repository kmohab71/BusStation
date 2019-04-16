package Malak_Khaled;

import dev.morphia.Datastore;

public class DB_config {
    public static Datastore datastore;
    public DB_config(Datastore datastore) {
        this.datastore=datastore;
    }
    public DB_config(){}
    public static void tripsAll(){


    }
}
