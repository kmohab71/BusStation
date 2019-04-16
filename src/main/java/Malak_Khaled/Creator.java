package Malak_Khaled;

public class Creator {
    public Creator() {
    }
    public User createUser(String name, String Username, String Password )
    {
        final User user= new User(name,Username,Password);
        System.out.println("user:"+user.getPassword()+"pass"+Password);
        DB_config.datastore.save(user);
        return user;
    }
    public manger createManger( String Name, int salary, String Username, String Password)
    {
        final manger elmer = new manger( Name,  salary,  Username,  Password);
        DB_config.datastore.save(elmer);
        return elmer;
    }
}
