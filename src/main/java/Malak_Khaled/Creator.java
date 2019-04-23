package Malak_Khaled;

import java.util.Date;

public class Creator {
    public Creator() {
    }
    public static User createUser(String name, String Username, String Password )
    {
        final User user= new User(name,Username,Password);
        System.out.println("user:"+user.getPassword()+"pass"+Password);
        DB_config.datastore.save(user);
        return user;
    }
    public static manger createManger( String Name, int salary, String Username, String Password)
    {
        final manger elmer = new manger( Name,  salary,  Username,  Password);
        DB_config.datastore.save(elmer);
        return elmer;
    }
    public static Car carCreat()
    {
        final Car lol = new Car( "malak",  new Date(2000,1,4),  5,  "www.google.com");
        DB_config.datastore.save(lol);
        return lol;
    }

}
