package Malak_Khaled;

import dev.morphia.annotations.Id;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;

public class Trip {
    @Id
    private ObjectId id;
    private String source;
    private String des;
    private boolean INTERNAL;
    private int StopsNum;
    private int price;
    private int VehicleType;
    private int NumPeopleLeft;
    private ObjectId driver;
    private Date date;
    private ArrayList<User> reservers= new ArrayList<>();




    public Trip(){}

    public Trip(String source, String des, boolean INTERNAL, int stopsNum, int price, int vehicleType,int NumPeopleLeft , ObjectId driver,int day,int month,int year) {
        this.source = source;
        this.des = des;
        this.INTERNAL = INTERNAL;
        this.StopsNum = stopsNum;
        this.price = price;
        this.driver=driver;
        this.VehicleType=vehicleType;
        this.NumPeopleLeft=NumPeopleLeft;
        this.date= new Date(year,month,day);
    }
    public Trip(Trip t) {
        this.source = t.des;
        this.des = t.source;
        this.INTERNAL = t.INTERNAL;
        this.StopsNum = t.StopsNum;
        this.price = t.price;
        this.driver=t.driver;
        this.StopsNum=t.StopsNum;
        this.VehicleType=t.VehicleType;
        this.date=t.date;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public void pushToReservers(User user)
    {
        this.reservers.add(user);
        System.out.println(reservers);
        Query<Trip> updateQuery =  DB_config.datastore.createQuery(Trip.class).disableValidation().field("id").equal(this.getId());
        UpdateOperations<Trip> ops1 =  DB_config.datastore.createUpdateOperations(Trip.class).addToSet("reservers",user);
        DB_config.datastore.update(updateQuery,ops1);

    }
    public void deleteTripsFromUsers()
    {
        for(int i=0;i<reservers.size();i++)
        {
            reservers.get(i).DeleteTrip(this);
        }
    }
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public boolean isINTERNAL() {
        return INTERNAL;
    }

    public void setINTERNAL(boolean INTERNAL) {
        this.INTERNAL = INTERNAL;
    }
    public boolean getINTERNAL() {
        return this.INTERNAL;
    }
    public int getStopsNum() {
        return StopsNum;
    }

    public void setStopsNum(int stopsNum) {
        StopsNum = stopsNum;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(int vehicleType) {
        VehicleType = vehicleType;
    }

    public int getNumPeopleLeft() {
        return NumPeopleLeft;
    }
    public void decrementNumPeopleLeft() {
        Query<Trip> DecTrips =  DB_config.datastore.createQuery(Trip.class).field("id").equal(this.id);
        UpdateOperations<Trip> ops =  DB_config.datastore.createUpdateOperations(Trip.class).dec("NumPeopleLeft");
        DB_config.datastore.update(DecTrips,ops);
        NumPeopleLeft--;
    }
    public void setNumPeopleLeft(int numPeopleLeft) {
        NumPeopleLeft = numPeopleLeft;
    }

    public ObjectId getDriver() {
        return driver;
    }

    public void setDriver(ObjectId driver) {
        this.driver = driver;
    }

    public ArrayList<User> getReservers() {
        return reservers;
    }

    public void setReservers(ArrayList<User> reservers) {
        this.reservers = reservers;
    }
}
