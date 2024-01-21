import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;

/*Westminster Shopping Manager class implements the ShoppingManager Interface according to the Interface Concept in ObjectOriented Programming principles*/
/*Westminster Shopping Manager class is created maintains the list of the products in the system and to
provide all the methods defined in the console menu.
*/
public class WestminsterShoppingManager implements ShoppingManager {

    /*Creates an empty Array List called product list declared as public static*/
    public static List<Product> productList = new ArrayList<>();
    private final static WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();

    /*In order to load the file without throwing any exception it has been wrapped up using try and catch block*/
    static {
        try {
            load_file();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /*Getter methods to get the product list*/
    public static List<Product> getProductList(){
        return productList;
    }

    /*get instance method follows a singleton pattern to ensure that only one instance of a class exists in the entire application
    get instance method follows a singleton pattern to ensure that only one instance of a class exists in the entire application*/
    public static WestminsterShoppingManager getInstance(){
        return westminsterShoppingManager;
    }




    /*Displays the Console menu when program is runned*/
    public void display_Console() throws IOException, ClassNotFoundException {
        Scanner menu_input = new Scanner(System.in);

        while(true){
            System.out.println("****************************************");
            System.out.println("Welcome to Westminster Shopping Manager");
            System.out.println("1. Add a New Product to System");
            System.out.println("2. Delete a Product from the System");
            System.out.println("3. Print the list of products in the System");
            System.out.println("4. Save in a file the list of products");
            System.out.println("5. Open GUI");
            System.out.println("6. Quit from the system");
            System.out.println("*****************************************");

            System.out.println("Enter your option ?");
            int option = menu_input.nextInt();

            switch (option){
                case 1:
                    /*Checks whether the system contains products below 50*/
                    if(productList.size() <= 50){
                        add_product();
                        System.out.println("Product has been added to list sucessfully");
                    }
                    else{
                        System.out.println("There are already 50 products in the system !!");
                        System.out.println("If you wish to add more, delete a product");
                    }
                    break;
                case 2:
                    delete_product();
                    break;
                case 3:
                    print_productList();
                    break;
                case 4:
                    save_file();
                    break;
                case 5:
                    load_GUI();
                    break;
                case 6:
                    System.out.println("Terminating from the system");
                    System.exit(0);
                    break;
                /*Default is used in order to display a messgae if the manager enters incorrect numbers that's not displayed in the console menu*/
                default:
                    System.out.println("The Value entered is Invalid");

            }
        }
    }

    /*add_Items method is overriden in order to add products to the product list*/
    @Override
    public void add_Items(Product product) throws IOException {
        productList.add(product);
        save_file();
    }

    /*remove_Items method is overriden in order to remove products from the product list
    * and saves the updated list to the file*/
    @Override
    public void remove_Items(Product product) throws IOException {
        if (productList.contains(product)){
            productList.remove(product);
            save_file();
            System.out.println("Product removed from the system successfully");
        }
        else{
            System.out.println("Product is not available in the system");
        }
    }


    /*add_product method allows users to add either electronics or clothing product to system*/
    private void add_product() throws IOException, ClassNotFoundException {
        Scanner product_input = new Scanner(System.in);

        System.out.println("Select the type of Product that you wish to add :");
        System.out.println("1. Electronics");
        System.out.println("2. Clothing");

        int product_type = product_input.nextInt();
        /*Validates the product type enterd by user. if its either 1 or 2 it prompts the user to enter details and add the product to system
        * else it goes to the default block and prints a message to Re check the product type option entered.*/
        if(product_type == 1 || product_type == 2){
            System.out.println("Enter Product ID : ");
            String productId = product_input.next();

            System.out.println("Enter Product Name : ");
            String productName = product_input.next();

            System.out.println("Enter No.of available items : ");
            int no_of_items_available = product_input.nextInt();

            System.out.println("Enter the Product Price : ");
            double price = product_input.nextDouble();

            switch (product_type){
                case 1:
                    System.out.println("Enter warranty period :");
                    int warranty_period = product_input.nextInt();

                    System.out.println("Enter the Brand : ");
                    String brand = product_input.next();

                    Electronics electronic = new Electronics(productId,productName,no_of_items_available,price,brand,warranty_period);
                    add_Items(electronic);
                    System.out.println("Electronic item added successfully !!");
                    break;
                case 2:
                    System.out.println("Enter size:");
                    String size = product_input.next();

                    System.out.println("Enter color:");
                    String color = product_input.next();

                    Clothing clothing_item = new Clothing(productId,productName,no_of_items_available,price,size,color);
                    add_Items(clothing_item);
                    System.out.println("The Clothing item added successfully !!");
                    break;
                default:
                    System.out.println("You have entered an Invalid Produt Type. Please Re-Check the Product type");
            }
        }
        /*Else block is executed if the user enters an option that is not present in the list of options given in the console*/
        else{
            System.out.println("You have entered an invalid option");
            display_Console();
        }


    }

    /*delete_product method deletes the products based on product ID.  */
    private void delete_product() throws IOException {
        System.out.println("Enter the Product ID to delete : ");
        Scanner input = new Scanner(System.in);
        String productID_Delete = input.nextLine();

        Product productToDelete = null;

        for (Product product: productList){
            if(product.getProductID().equals(productID_Delete)){
                productToDelete = product;
                break;
            }
        }
        /*Checks whether the Product ID is  null */
        if(productToDelete != null){
            remove_Items(productToDelete);
            System.out.println("The Product has been deleted successfully !!");
            display_productInfo(productToDelete);
            System.out.println("Total number of products left in the system : "+ productList.size());

        }
        else{
            System.out.println("Product is not available in the system ");
        }
    }


    /*The display product info method display the details of product */
    private void display_productInfo(Product product){
        System.out.println("Product ID : " + product.getProductID());
        System.out.println("Product Name : " + product.getProductName());
        System.out.println("No of Items Available :" + product.getNo_of_items_available());
        System.out.println("Price : "+ product.getPrice());

        /*Instance of method is used to check whether its electronic or clothing product before displaying*/
        if(product instanceof Electronics){
            System.out.println("Type : Electronics");
            System.out.println("Brand : " + ((Electronics)product).getBrand());
            System.out.println("Warranty Period : " + ((Electronics)product).getWarranty_period());
        }
        if(product instanceof Clothing){
            System.out.println("Type : Electronics");
            System.out.println("Size : " + ((Clothing)product).getSize());
            System.out.println("Color : " + ((Clothing)product).getColor());

        }
    }

    /*The print_productList method prints the sorted list of product details that were sorted based on product ID using collections.sort method. */
    public void print_productList(){
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getProductID().compareTo(p2.getProductID());
            }
        });
        System.out.println("\n ****Product List ****");
        for(Product product:productList){
            display_productInfo(product);
        }
    }

    /*The save_file method saves the productlist to a file called Product Details.txt */
    @Override
    public void save_file() throws IOException {
        FileOutputStream file_out = new FileOutputStream("Product Details.txt");
        ObjectOutputStream out = new ObjectOutputStream(file_out);
        out.writeObject(productList);
        out.close();
        file_out.close();

        System.out.println("The Product List has been saved in the file !!");
    }


    /*The load_file method load the file each time the program is run */
    private static void load_file() throws IOException, ClassNotFoundException {
       FileInputStream file_in = new FileInputStream("Product Details.txt");
       ObjectInputStream infile = new ObjectInputStream(file_in);
       productList = (List<Product>)  infile.readObject();

        infile.close();
        file_in.close();

        System.out.println(productList);
    }

    /*The ShoppingCartGUI method overrides the interface method to open the gui.*/
    @Override
    public void ShoppingCartGUI() throws IOException, ClassNotFoundException {
        try {
            SwingUtilities.invokeLater(() -> {
                try {
                    ShoppingCartGUI();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            System.out.println("Error in openeing GUI :" + e.getMessage());
        }
    }

    /*The load_GUI method opens the gui.*/
    public void load_GUI() throws IOException, ClassNotFoundException {
        new ShoppingCartGUI();

    }
}


