/*Clothing class extends the product class applying the inheritance concept
 * Clothing class is created to represent the details of Clothing*/
class Clothing extends Product {

    /*Size and Color is declared as private instance variables*/
    private String size;
    private String color;

    /*A Parametrized Constrtutor which has the parameters inherited from Product Class along with Electronic class instance variables brand and warranty period*/
    public Clothing(String productID, String productName, int no_of_items_available, double price, String size, String color) {
        super(productID, productName, no_of_items_available, price);
        this.size = size;
        this.color = color;
    }

    /*Getter methods to get the Size and Color*/
    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    /*Setter methods to set the Size and Color*/
    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    /*toString() represents the visual representation of Clothing object*/
     @Override
     public String toString() {
         return "Clothing{" +
                 "size='" + size + '\'' +
                 ", color='" + color + '\'' +
                 '}';
     }
 }
