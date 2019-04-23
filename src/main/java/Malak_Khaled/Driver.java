package Malak_Khaled;

import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

import java.util.List;

import static Malak_Khaled.DB_config.datastore;

public class Driver extends Employee {
    @Id
    private ObjectId id;
    public Driver(){}
    public Driver( int salary,String name, String username, String password) {
        setSalary(salary);
        setName(name);
        setUsername(username);
        setPassword(password);
    }
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
    public int getSalary(){ return super.getSalary(); }
    public String getName(){
        return super.getName();
    }
    public String getUsername(){
        return super.getUsername();
    }
    public String getPassword(){
        return super.getPassword();
    }
    public ObjectId getId(){
        return id;
    }
    public List<Trip> ViewTripDriver()
    {
        List<Trip> seq = datastore.find(Trip.class, "driver",getId()).asList();
        for (int i=0;i<seq.size();i++)
            System.out.println(seq.get(i).getSource()+' '+seq.get(i).getDes());
        return  seq;
    }
}


