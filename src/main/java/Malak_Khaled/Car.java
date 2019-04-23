package Malak_Khaled;


import java.util.Date;

public class Car implements Vehicle {

        private String name;
        private String picURL;

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    private Date date;
        private int numOfDays;
        private double price;
    public Car(){}
    public Car(String name, Date date, int numOfDays,String picURL) {
        this.name = name;
        this.date = date;
        this.numOfDays = numOfDays;
        this.picURL=picURL;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void calculatePrice(){

        price=50*numOfDays;
        }

}


