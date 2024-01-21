 /*Electronics class extends the product class applying the inheritance concept
 * Electronic class is created to represent the details of electronics*/
class Electronics extends Product {
     /*Brand and warranty_period is declared as private instance variables*/
    private String brand;
    private int warranty_period;

    /*A Parametrized Constrtutor which has the parameters inherited from Product Class along with Electronic class instance variables brand and warranty period*/
    public Electronics(String productID, String productName, int no_of_items_available, double price, String brand, int warranty_period) {
        super(productID, productName, no_of_items_available, price);
        this.brand = brand;
        this.warranty_period = warranty_period;
    }


     /*Getter methods to get the Brand and Warranty Period*/
    public String getBrand() {
        return brand;
    }

    public int getWarranty_period() {
        return warranty_period;
    }

     /*Setter methods to set the Brand and Warranty Period*/
     public void setBrand(String brand) {
         this.brand = brand;
     }

    public void setWarranty_period(int warranty_period) {
        this.warranty_period = warranty_period;
    }

    /*toString() represents the visual representation of electronics object*/
     @Override
     public String toString() {
         return "Electronics{" +
                 "brand='" + brand + '\'' +
                 ", warranty_period=" + warranty_period +
                 '}';
     }
 }
