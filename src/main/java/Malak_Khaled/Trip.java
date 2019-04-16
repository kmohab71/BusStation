package Malak_Khaled;

import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

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

    public Trip(){}

    public Trip(String source, String des, boolean INTERNAL, int stopsNum, int price, int vehicleType,int NumPeopleLeft , ObjectId driver) {
        this.source = source;
        this.des = des;
        this.INTERNAL = INTERNAL;
        this.StopsNum = stopsNum;
        this.price = price;
        this.driver=driver;
        this.VehicleType=vehicleType;
        this.NumPeopleLeft=NumPeopleLeft;
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
    }
}
