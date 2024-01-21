import java.io.IOException;
/*Shopping Manager has been defined as an Interface
it contains all the methods that were implemented in Westminster shopping manager class */
public interface ShoppingManager {
    void display_Console() throws IOException, ClassNotFoundException;

    void add_Items(Product product) throws IOException;

     void remove_Items(Product product) throws IOException;

     void print_productList();

     void save_file() throws IOException;

     void ShoppingCartGUI() throws IOException, ClassNotFoundException;


}
