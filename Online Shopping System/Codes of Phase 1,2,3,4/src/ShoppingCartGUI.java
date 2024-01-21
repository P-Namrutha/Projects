import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShoppingCartGUI extends JFrame {
    private WestminsterShoppingManager shoppingManager;
    private JComboBox<String> productCatergory;
    private JLabel searchField;
    private JButton viewShoppingCartButton;
    private JPanel detailsPanel;
    private JTable table;
    private JTable cartTable;

    // Create the total price label
    private JLabel totalPriceLabel = new JLabel();

    private double totalPrice;



    private DefaultTableModel cartTableModel;

    private Set<JFrame> openFrames = new HashSet<>();

    public ShoppingCartGUI() throws IOException, ClassNotFoundException {
        shoppingManager = new WestminsterShoppingManager();

        createGUI();
    }

    private void createGUI() {
        setTitle("Westminster Shopping Centre");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 200);


        // Create table
        String[][] data = {
                {"Product ID", "Name", "Category", "Price", "Info"}
        };
        String[] columnNames = {"Product ID", "Name", "Category", "Price", "Info"};
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Create the top panel with combo box
        String[] productCategories = {"All", "Electronics", "Clothing"};
        productCatergory = new JComboBox<>(productCategories);
        productCatergory.setPreferredSize(new Dimension(200, 30));
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(productCatergory);

        // Create the center panel with combo box and scroll pane
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(topPanel);
        centerPanel.add(scrollPane);

        add(centerPanel, BorderLayout.CENTER);

        // Create the details panel with a label
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 60, 35));
        JLabel label = new JLabel("Selected Product Details: ");
        detailsPanel.add(label);
        add(detailsPanel, BorderLayout.SOUTH);

        setVisible(true);


        productCatergory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // You can add code here to handle the selection change
                String selectedCategory = (String) productCatergory.getSelectedItem();
                System.out.println("Selected Category: " + selectedCategory);
                updateTableData(productCatergory, tableModel);

            }
        });


        // Create the Select the Product Catergory text field
        searchField = new JLabel("Select Product Catergory");
        /*searchField.setText("Select Product Catergory");*/
        searchField.setPreferredSize(new Dimension(150, 20));
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 60, 35));
        textPanel.add(searchField);
        add(textPanel, BorderLayout.WEST);


        //Create the view shopping cart button
        viewShoppingCartButton = new JButton("View Shopping Cart");
        viewShoppingCartButton.setPreferredSize(new Dimension(150, 25));

        // Add the button to a panel in the EAST Region
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(viewShoppingCartButton);
        add(buttonPanel, BorderLayout.EAST);

        // Add action listener to the button
        viewShoppingCartButton.addActionListener(this::viewShoppingCartButtonClicked);

        setVisible(true);

    }

    private void viewShoppingCartButtonClicked(ActionEvent e) {
        ShoppingCartFrame shoppingCartFrame = new ShoppingCartFrame();
        shoppingCartFrame.setVisible(true);

        openFrames.add(shoppingCartFrame);

        // When the frame is closed, remove it from the set
        shoppingCartFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                openFrames.remove(shoppingCartFrame);
            }
        });
        updateShoppingCartTable();


    }

    private void updateShoppingCartTable() {
        // Retrieve selected product details from the main table
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String productID = table.getValueAt(selectedRow, 0).toString();
            String productName = table.getValueAt(selectedRow, 1).toString();
            /*String category = table.getValueAt(selectedRow, 2).toString();*/
            String price = table.getValueAt(selectedRow, 3).toString();
            String info = table.getValueAt(selectedRow, 4).toString();

            // Prompt the user for quantity
            String quantityString = JOptionPane.showInputDialog(this, "Enter Quantity for " + productName + ":");
            int quantity = 0;

            try {
                // Parse quantity input
                quantity = Integer.parseInt(quantityString);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid quantity input. Please enter a valid number.");
                return; // Exit if quantity input is not a valid number
            }

            // Concatenate product details into a single string
            String productDetails = productID + "\n" + productName + "\n" + info;

            // Remove category information from the product details
            productDetails = productDetails.replace(", Category: " + productCatergory.getSelectedItem(), "");

            // Calculate the updated price based on the quantity
            double updatedPrice = Double.parseDouble(price) * quantity;

            // Format the updated price to two decimal places
            String formattedPrice = String.format("%.2f", updatedPrice);

            // Add selected product details to the shopping cart table
            cartTableModel = (DefaultTableModel) cartTable.getModel();
            cartTableModel.addRow(new String[]{productDetails, String.valueOf(quantity),formattedPrice});
            totalPrice = Double.valueOf(formattedPrice) + totalPrice;
            totalPriceLabel.setText("Total : " + Math.round(totalPrice * Math.pow(10,2)) / Math.pow(10,2));

        }
    }

    private void updateTableData(JComboBox productCategoryComboBox, DefaultTableModel tableModel) {
        // Get the selected category from the combo box
        String selectedCategory = (String) productCategoryComboBox.getSelectedItem();


        // Clear the existing data in the table
        tableModel.setRowCount(0);
        List<Product> productList = WestminsterShoppingManager.getInstance().getProductList();

        for (Product product : productList){
            if ((selectedCategory.equalsIgnoreCase("All") || selectedCategory.equalsIgnoreCase("electronics")) && product instanceof Electronics){
                Electronics electronics = (Electronics) product ;
                String[] rowData = {electronics.getProductID(), electronics.getProductName(), "Electronics", String.valueOf(electronics.getPrice()),electronics.getBrand() + " , " + electronics.getWarranty_period()};

                tableModel.addRow(rowData);
            }
            if ((selectedCategory.equalsIgnoreCase("All") || selectedCategory.equalsIgnoreCase("clothing")) && product instanceof Clothing){
                Clothing clothing = (Clothing) product ;
                String[] rowData = {clothing.getProductID(), clothing.getProductName(), "Clothing", String.valueOf(clothing.getPrice()),clothing.getSize() + " , " + clothing.getColor()};

                tableModel.addRow(rowData);
            }

            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Display panel based on the selected row
                        displayDetailsPanel(selectedRow);
                    }
                }
            });

        }

    }

    private void displayDetailsPanel(int selectedRow) {
        // Retrieve data from the selected row
        String productID = table.getValueAt(selectedRow, 0).toString();
        String productName = table.getValueAt(selectedRow, 1).toString();
        String category = table.getValueAt(selectedRow, 2).toString();
        String price = table.getValueAt(selectedRow, 3).toString();
        String info = table.getValueAt(selectedRow, 4).toString();

        // Update detailsPanel with selected product details
        detailsPanel.removeAll();
        // Use GridLayout for vertical arrangement
        detailsPanel.setLayout(new GridLayout(0, 1));

        // Add JLabels for each cell in the row
        detailsPanel.add(new JLabel("Selected Product Details: "));
        detailsPanel.add(new JLabel("ID: " + productID));
        detailsPanel.add(new JLabel("Name: " + productName));
        detailsPanel.add(new JLabel("Category: " + category));
        detailsPanel.add(new JLabel("Price: " + price));
        detailsPanel.add(new JLabel("Info: " + info));
        /*JLabel label = new JLabel("Selected Product Details: " + "\n"+
                "ID=" + productID + "\n"+
                ", Name=" + productName + "\n"+
                ", Category=" + category + "\n"+
                ", Price=" + price + "\n"+
                ", Info=" + info);*/
        //detailsPanel.add(label);
        /*Add a button called Add to Shopping Cart below the Selected Product Details*/
        JButton addToCartButton = new JButton("Add to Shopping Cart");
        addToCartButton.setPreferredSize(new Dimension(90,25));
        addToCartButton.addActionListener(e ->{
            if (openFrames.size() > 0){
                updateShoppingCartTable();
            }


        });
        detailsPanel.add(addToCartButton);
        detailsPanel.revalidate();
        detailsPanel.repaint();
    }

    //Method to display product details in the detailsPanel

    private void displayProductDetails(JPanel detailsPanel,Product product){
        //Create JTextArea to display product details
        JTextArea details = new JTextArea();
        details.setEditable(false);
        details.setText("Product ID :" + product.getProductID() + "\n"+ "Catergory : " + getProductCatergory(product) + "\n" + "Name :"+ product.getProductName()+ "Price" + product.getPrice());

        //Add details to the detailsPanel
        detailsPanel.add(details);
        detailsPanel.revalidate();
        detailsPanel.repaint();
    }


    //Method to get prodcut catergory as a string
    private String getProductCatergory (Product product){
        if(product instanceof Electronics){
            return "Electronics";
        } else if (product instanceof Clothing) {
            return "Clothing";

        } else {
            return "Unknown";
        }
    }
    public class ShoppingCartFrame extends JFrame{
        public ShoppingCartFrame(){
            setTitle("Shopping Cart");
            setSize(400,300);

            // Create table for shopping cart
            String[][] cartData = {
                    {"Product", "Quantity", "Price"}

            };
            String[] cartColumnNames = {"Product", "Quantity", "Price"};
            DefaultTableModel cartTableModel = new DefaultTableModel(cartData, cartColumnNames);
            cartTable = new JTable(cartTableModel);
            JScrollPane cartScrollPane = new JScrollPane(cartTable);




            // Add the table to the content pane
            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(cartScrollPane, BorderLayout.CENTER);
            getContentPane().add(totalPriceLabel, BorderLayout.SOUTH);


        }


}
    /*Create a User account frame*/
   /* public class UserFrame extends JFrame{
        private JTextField usernameField;
        private JPasswordField passwordField;

        public UserFrame(){
            setTitle("User Login");
            setSize(300,150);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel usernameLabel = new JLabel("Username : ");
            JLabel passwordLabel = new JLabel("Password : ");

            usernameField = new JTextField(15);
            passwordField = new JPasswordField(15);

            JButton login = new JButton("Login");
        }
    }
*/


}
