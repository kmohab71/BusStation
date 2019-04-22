package Malak_Khaled;

import com.mongodb.BasicDBObject;
import dev.morphia.annotations.Id;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


public class User extends Person{
    private ArrayList<ReservedTikets> reserved= new ArrayList<ReservedTikets>();
    @Id
    private static ObjectId id;

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
            Query<Trip> TripQuery =  DB_config.datastore.createQuery(Trip.class).field("source").equal(trip.getDes());
            List<Trip> TripList = TripQuery.field("des").equal(trip.getSource()).asList();
            if (!TripList.isEmpty())
            {
                Trip trip2 = TripList.get(0);
                ReservedTikets reserve1=new ReservedTikets(trip2, twoWay,  FirstClass);
                reserve1.calculatePrice();
                UpdateOperations<User> ops1 =  DB_config.datastore.createUpdateOperations(User.class).addToSet("reserved",reserve1);
                DB_config.datastore.update(updateQuery,ops1);

            }

            ReservedTikets reserve=new ReservedTikets(trip, twoWay,  FirstClass);
            reserve.calculatePrice();
            UpdateOperations<User> ops =  DB_config.datastore.createUpdateOperations(User.class).addToSet("reserved",reserve);
            DB_config.datastore.update(updateQuery,ops);

        }
        else
        {
            Query<User> updateQuery =  DB_config.datastore.createQuery(User.class).field("Username").equal(getUsername());
            ReservedTikets reserve=new ReservedTikets(trip, twoWay,  FirstClass);
            reserve.calculatePrice();
            UpdateOperations<User> ops =  DB_config.datastore.createUpdateOperations(User.class).addToSet("reserved",reserve);
            DB_config.datastore.update(updateQuery,ops);
        }
            trip.pushToReservers(this);
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
        super.setPassword(Password);
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

    public void DeleteTrip(Trip trip)
    {
        final User UsertoUpdate = DB_config.datastore.find(User.class).disableValidation().field("id").equal(this.getId()).get();
        System.out.println(UsertoUpdate);
        ReservedTikets reservedtoDel=null;
        //final UpdateOperations<User> updateOperations = DB_config.datastore.createUpdateOperations(User.class).disableValidation().removeAll("reserved", new BasicDBObject("id",id));
        for(int i=0 ;i<this.getReserved().size();i++)
            if(this.getReserved().get(i).getTrip().equals(trip))
                reservedtoDel=this.getReserved().get(i);
        System.out.println(reservedtoDel);
        if (reservedtoDel != null)
        {
            UpdateOperations<User> updateOperations = DB_config.datastore.createUpdateOperations(User.class).disableValidation().removeAll("reserved",new BasicDBObject("trip", new BasicDBObject("id", trip.getId())));
            System.out.println(updateOperations);
            DB_config.datastore.update(UsertoUpdate, updateOperations);
        }

    }

}
