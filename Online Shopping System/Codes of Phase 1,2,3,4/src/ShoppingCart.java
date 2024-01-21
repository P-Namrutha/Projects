import java.util.ArrayList;
import java.util.List;
/* Shopping Cart class declared as public in order to hold the list of products.*/

public class ShoppingCart {
    private List <Product> productList = new ArrayList<>();

    //Defining an array list in a constructor
    public ShoppingCart(List <Product> productList) {
        this.productList = productList;
    }

    /*Adds products to the product list*/
    public void add_product(Product product){
        productList.add(product);
    }
    /*Remove products from the product list based on Product ID*/
    public void remove_Items(Product product,String productId) {productList.remove(product);}

    /*public static void total_cost(){}*/


}
