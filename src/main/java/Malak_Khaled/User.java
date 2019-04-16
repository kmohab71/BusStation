package Malak_Khaled;

import dev.morphia.annotations.Id;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import org.bson.types.ObjectId;

import java.util.ArrayList;


public class User extends Person{
    private ArrayList<ReservedTikets> reserved= new ArrayList<ReservedTikets>();
    @Id
    private static ObjectId id;
    //ReservedTikets reserved[]=new ReservedTikets[10];

    public User (){}

    public User(String name, String Username,String Password)
    {
        setName(name);
        setUsername(Username);
        setPassword(Password);
    }

    public ArrayList<ReservedTikets> getReserved() {
        return reserved;
    }

    public void setReserved(ArrayList<ReservedTikets> reserved) {
        this.reserved = reserved;
    }

    public static void setId(ObjectId id) {
        User.id = id;
    }

    public ObjectId getId() {
        return id;
    }
    public void addTriptoUser(Trip trip, boolean twoWay, boolean FirstClass)
    {
        if(trip.getNumPeopleLeft()>0){
        if(twoWay==true)
        {
            Query<User> updateQuery =  DB_config.datastore.createQuery(User.class).field("Username").equal(getUsername());

            Trip trip2= new Trip(trip);
            ReservedTikets reserve=new ReservedTikets(trip, twoWay,  FirstClass);
            ReservedTikets reserve1=new ReservedTikets(trip2, twoWay,  FirstClass);
            reserve.calculatePrice();
            reserve1.calculatePrice();
            UpdateOperations<User> ops =  DB_config.datastore.createUpdateOperations(User.class).addToSet("reserved",reserve);
            DB_config.datastore.update(updateQuery,ops);
            UpdateOperations<User> ops1 =  DB_config.datastore.createUpdateOperations(User.class).addToSet("reserved",reserve1);
            DB_config.datastore.update(updateQuery,ops1);
        }
        else
        {
            Query<User> updateQuery =  DB_config.datastore.createQuery(User.class).field("Username").equal(getUsername());
            ReservedTikets reserve=new ReservedTikets(trip, twoWay,  FirstClass);
            reserve.calculatePrice();
            UpdateOperations<User> ops =  DB_config.datastore.createUpdateOperations(User.class).addToSet("reserved",reserve);
            DB_config.datastore.update(updateQuery,ops);
        }
            trip.decrementNumPeopleLeft();
        }else{}
    }
    public void setName(String name){
        super.setName(name);
    }
    public void setUsername(String Username){
        super.setUsername(Username);
    }
    public void setPassword(String Password){
        super.setUsername(Password);
    }
    public String getName(){
        return super.getName();
    }
    public String getUsername(){
        return super.getUsername();
    }
    public String getPassword(){
        return super.getPassword();
    }
    public User getinfo()
    {
        Query<User> Query =  DB_config.datastore.createQuery(User.class).field("Username").equal(getUsername());
        return Query.asList().get(0);

    }



}
