import java.io.Serializable;
/*Product Class created as an abstract class and it implements a serializable interface.
Product class act as the super class for electronics and clothing class*/
public abstract class Product implements Serializable {

    /*product ID,productName, No_of_items_available and price are defined as private instance variables*/
    private String productID;
    private String productName;
    private int No_of_items_available;
    private double price;

    /*Parametrized Constructor*/
    public Product(String productID, String productName, int no_of_items_available, double price) {
        this.productID = productID;
        this.productName = productName;
        No_of_items_available = no_of_items_available;
        this.price = price;
    }

    /*Getter methods to get the Product ID, Product Name, No of items available, Price */
    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }
    public int getNo_of_items_available() {
        return No_of_items_available;
    }
    public double getPrice() {
        return price;
    }

    /*Setter methods to set the Product ID, Product Name, No of items available, Price */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setNo_of_items_available(int no_of_items_available) {
        No_of_items_available = no_of_items_available;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /*toString() represents the visual representation of product object*/
    @Override
    public String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", No_of_items_available=" + No_of_items_available +
                ", price=" + price +
                '}';
    }
}
