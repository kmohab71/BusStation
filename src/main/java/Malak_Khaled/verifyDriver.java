package Malak_Khaled;

import dev.morphia.query.Query;

import java.util.List;

public class verifyDriver {
    public verifyDriver() {

    }

    private static Driver verifyUserName(String username, String password) {

        Query query = DB_config.datastore.createQuery(Driver.class)
                .field("Username").equal(username);
        System.out.println(query.asList());
        if (!query.asList().isEmpty()) {
            Driver driver = (Driver) query.asList().get(0);
            if (driver.getPassword().equals(password))
                return driver;
            }
        return null;
    }

    public static Driver getDriver(String username, String password) {
        Driver driver =verifyUserName(username,password);
        return driver;
    }
    public static List<Trip>  getDrivertrips(String username, String password) {
        Driver driver = verifyUserName(username,password);
        if(driver!=null){
            List<Trip> trips=driver.ViewTripDriver();
            return trips;
        }
        return null;
    }
    public static List<Trip>  getDrivertrips(Driver driver) {
        if(driver!=null){
            List<Trip> trips=driver.ViewTripDriver();
            return trips;
        }
        return null;
    }
}
