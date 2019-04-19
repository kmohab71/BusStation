package Malak_Khaled;

import dev.morphia.query.Query;
import org.junit.Assert;

import java.util.List;

public class manger extends Employee {

    public manger() {
   }

    public manger(String Name, int salary, String Username, String Password) {

        setSalary(salary);
        setName(Name);
        setUsername(Username);
        setPassword(Password);
    }

    public Trip addTrip(String source, String des, boolean INTERNAL, int vehicleType, int NumPeopleLeft , int stopsNum, int price, String Drivername, int day, int month,int year)
    {
        List<Driver> driver = DB_config.datastore.find(Driver.class)
                .filter("name =", Drivername)
                .asList();
        Assert.assertEquals(1, driver.size());
        //System.out.println(driver.get(0).getId());
         final Trip newtrip = new Trip(source, des,INTERNAL, stopsNum,price,vehicleType, NumPeopleLeft,driver.get(0).getId(),day,month,year);
         final Trip newtrip2 = new Trip(des,source,INTERNAL, stopsNum, price,vehicleType, NumPeopleLeft,driver.get(0).getId(),day,month,year);
         DB_config.datastore.save(newtrip);
         DB_config.datastore.save(newtrip2);
         return newtrip;
    }
    public Driver addDriver(int salary,String name, String username, String password)
    {
        final Driver driver = new Driver(salary ,name,  username, password);
        DB_config.datastore.save(driver);
        return driver;
    }

    @Override
    public void setSalary(int salary) {
        super.setSalary(salary);
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

    public void deleteTrip(Trip trip)
    {
        //System.out.println(trip.id);
        DB_config.datastore.delete(Trip.class ,trip.getId());
    }

    public  List<Trip> ViewTrips()
    {
        List<Trip> Trips = DB_config.datastore.find(Trip.class).asList();
        System.out.println(Trips);
        return Trips;
    }
    public void deleteTrip(String source , String des)
    {
        Query<Trip> DeletedTrip =  DB_config.datastore.createQuery(Trip.class).field("source").equal(source);
        List<Trip> DeletedTripList = DeletedTrip.field("des").equal(des).asList();
        if (!DeletedTripList.isEmpty())
        {
            DB_config.datastore.delete(Trip.class ,DeletedTripList.get(0).getId());
        }
    }
    //public void editTrip(Trip trip){}

}


