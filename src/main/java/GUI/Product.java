package GUI;

public class Product {

    private String source;
    private String destination;
    private int numberOfStops;

    public Product(){
        this.source = "";
        this.destination = "";
        this.numberOfStops = 0;
    }

    public Product(String source, String destination, int numberOfStops){
        this.source = source;
        this.destination = destination;
        this.numberOfStops = numberOfStops;
    }

    public String getName() {
        return source;
    }

    public void setName(String name) {
        this.source = name;
    }

    public String getPrice() {
        return destination;
    }

    public void setPrice(String price) {
        this.destination = price;
    }

    public int getQuantity() {
        return numberOfStops;
    }

    public void setQuantity(int quantity) {
        this.numberOfStops = quantity;
    }

}