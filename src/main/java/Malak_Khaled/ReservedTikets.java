package Malak_Khaled;

public class ReservedTikets {
    private Trip trip;
    private boolean TwoWays;
    private boolean FirstClass;
    private float Price;

    private ReservedTikets(){}

    public ReservedTikets(Trip trip, boolean twoWays, boolean firstClass) {
        this.trip = trip;
        this.TwoWays = twoWays;
        this.FirstClass = firstClass;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public boolean isTwoWays() {
        return TwoWays;
    }

    public void setTwoWays(boolean twoWays) {
        TwoWays = twoWays;
    }

    public boolean isFirstClass() {
        return FirstClass;
    }

    public void setFirstClass(boolean firstClass) {
        FirstClass = firstClass;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public void calculatePrice()
    {
        int twoways= TwoWays ? 1 : 0;

        if (FirstClass==true && !trip.getINTERNAL()) {
            this.Price= (float) (trip.getPrice()*2-(twoways*trip.getPrice()*0.2));

        }else if(FirstClass==false && !trip.getINTERNAL()){
            this.Price= (float) (trip.getPrice()*1.75-(twoways*trip.getPrice()*0.2));

        }else if(FirstClass==true && trip.getINTERNAL()){

            this.Price= (float) (trip.getPrice()*1.5-(twoways*trip.getPrice()*0.4));
        }else{
            this.Price= (float) (trip.getPrice()*1.25-(twoways*trip.getPrice()*0.4));
        }
    }
}
