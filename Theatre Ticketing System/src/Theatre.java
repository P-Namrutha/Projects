import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;

public class Theatre {
    /* Creates an array for each row*/
    private static final int[] row1 = new int[12];
    private static final int[] row2 = new int[16];
    private final static int[] row3 = new int[20];
    private final static ArrayList<Ticket> tickets = new ArrayList<>();/* Creates an array list to save all the tickets*/
    public static void main(String[] args) {
        System.out.println("Welcome to the New Theatre");
        /* The infinite loop prompts the user again and again to enter an option until the user breaks the loop*/
        while (true) {
            System.out.println("-------------------------------------------------");
            System.out.println("Please select an option :");
            System.out.println("1) Buy a ticket ");
            System.out.println("2) Print seating area ");
            System.out.println("3) Cancel ticket");
            System.out.println("4) List available seats");
            System.out.println("5) Save to file");
            System.out.println("6) Load from file");
            System.out.println("7) Print ticket information and total price");
            System.out.println("8) Sort tickets by price");
            System.out.println("0) Quit");
            System.out.println("--------------------------------------------------");
            Scanner input = new Scanner(System.in);
            System.out.println("Enter an option :");
            int option = input.nextInt();

            /* Calling each method */
            if(option == 1) {
                buy_ticket();
            }

            else if (option == 2) {
                print_seating_area();
            }
            else if(option == 3){
                cancel_ticket();
            }
            else if(option == 4){
                show_available(row1);
                show_available(row2);
                show_available(row3);
            }
            else if(option == 5){
                save(row1,row2,row3);
            }
            else if(option == 6){
                load();
            }
            else if(option == 7){
                show_tickets_info();
            }
            else if(option == 8){
                sort_tickets();
            }
            else if (option == 0) {
                break;
            }
            else{
                System.out.println("Ïnvalid Option !");
            }
        }



    }
    /* Task 3 & 12*/

    public static void buy_ticket() {
        Scanner input = new Scanner(System.in);

        /* In Task 12 , when buying a ticket asks the user for their personal info */
        System.out.println("Enter your firstName :");
        String firstname = input.next();

        System.out.println("Enter your Surname :");
        String Surname = input.next();

        System.out.println("Enter your Email :");
        String Email = input.next();

        System.out.println("Enter the price :");
        int price = input.nextInt();

        /* Validates the row number and seat number*/
        int rownumber = 0;
        int seatnumber = 0;
        boolean isCorrect = true;
        while (isCorrect) {
            try {
                System.out.print("Enter row number (1-3): ");
                rownumber = input.nextInt();
                System.out.print("Enter seat number: ");
                seatnumber = input.nextInt();
                if (rownumber == 1 && seatnumber <= 12 && seatnumber>=1) {
                    isCorrect = false;
                } else if (rownumber == 2 && seatnumber <= 16 && seatnumber>=1) {
                    isCorrect = false;
                } else if (rownumber == 3  && seatnumber <= 20 && seatnumber>=1) {
                    isCorrect = false;
                } else {
                    System.out.println("Invalid row or seat number, please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a valid number.");
                input.nextLine();
            }
        }

        switch (rownumber) {
            case 1 -> {
                if (row1[seatnumber - 1] == 0) {
                    row1[seatnumber - 1] = 1;
                    System.out.println("\n You have purchased your ticket successfully");
                } else {
                    System.out.println("\n The seat that you have selected has already been sold !");
                }

            }
            case 2 -> {
                if (row2[seatnumber - 1] == 0) {
                    row2[seatnumber - 1] = 1;
                    System.out.println("\n You have purchased your ticket successfully");
                } else {
                    System.out.println("\n The seat that you have selected has already been sold !");
                }
            }
            case 3 -> {
                if (row3[seatnumber - 1] == 0) {
                    row3[seatnumber - 1] = 1;
                    System.out.println("\n You have purchased your ticket successfully");
                } else {
                    System.out.println("\n The seat that you have selected has already been sold !");
                }
            }
        }
        Person human = new Person(firstname, Surname, Email);/* Calling the constructor*/
        Ticket ticket_1 = new Ticket(rownumber, seatnumber, price, human);
        tickets.add(ticket_1); /* Adds the ticket_1 to array called tickets*/

    }


    /* Task 4*/
    public static void print_seating_area() {
        System.out.println("***********");
        System.out.println("*  Stage  *");
        System.out.println("***********");
        for (int i = 0; i < row1.length; i++) {
            if (row1[i] == 1) { /* Here the i refers to the index of the array. And the if loop checks whether row the seat in that particualr index is occupied or not*/
                System.out.print("X");
            } else {
                System.out.print("O");
            }
        }
        System.out.println();
        for (int i = 0; i < row2.length; i++) {
            if (row2[i] == 1) {
                System.out.print("X");
            } else {
                System.out.print("O");
            }
        }
        System.out.println();
        for (int i = 0; i < row3.length; i++) {
            if (row3[i] == 1) {
                System.out.print("X");
            } else {
                System.out.print("O");
            }

        }
        System.out.println();
    }


    /* Task 5 */
    public static void cancel_ticket(){
        Scanner cancel = new Scanner(System.in);

        System.out.println("Enter row number :");
        int rownumber = cancel.nextInt();

        System.out.println("Enter Seat number :");
        int seatnumber = cancel.nextInt();

        switch (rownumber){
            case 1 -> {
                /* Checks whether the seat is already booked*/
                if (row1[seatnumber-1] == 1 ) {
                    row1[seatnumber-1]=0; /* Cancelling the ticket*/
                    System.out.println("\n This ticket has been cancelled");
                } else{
                    System.out.println("\n The seat has been sold");
                }

            }
            case 2 -> {
                if (row2[seatnumber -1] == 1){
                    row2[seatnumber - 1] = 0;
                    System.out.println("\n This ticket has been cancelled");
                } else{
                    System.out.println("\n The seat has been sold");
                }
            }
            case 3 -> {
                if (row3[seatnumber -1] == 1){
                    row3[seatnumber - 1] = 0;
                    System.out.println("\n This ticket has been cancelled");
                } else{
                    System.out.println("\n This ticket has been sold");
                }
            }
        }
        /* Loops through the array list and checks for row number and seatnumber that's in the tickets array*/
        /* Removes the particular ticket that's in the tickets array */
        tickets.removeIf(ticket_1 -> rownumber == ticket_1.getRow() && seatnumber == ticket_1.getSeat());

    }
    /* Task 6*/
    public static void show_available(int[]list){ /* List represents the seats that are not booked*/
        for(int i=0;i<list.length;i++){
            /* Checks whether seat is not booked */
            if (list[i]==0){
                System.out.print((i+1)+" ");/* Prints the list of seats that are not booked*/
            }
        }
        System.out.println();
    }

    /* Task 7*/
    public static void save(int[]row1,int[]row2,int[]row3){
        try{
            FileWriter info = new FileWriter("Saved-seats.txt");
            for(int i=0; i< row1.length;i++){
                info.write(row1[i] + ",");
            }
            info.write("\n");
            for(int i=0; i< row2.length;i++){
                info.write(row2[i] + ",");

            }
            info.write("\n");
            for(int i=0; i< row3.length;i++){
                info.write(row3[i] + ",");

            }
            info.write("\n");
            info.close();
            System.out.println("The seats information has been saved "+ "Saved-seats.txt");
        }
        catch(IOException e){
            System.out.println("An error occured in saving the file");
            e.printStackTrace();

        }
    }
    /* Task 8 */
    public static void load(){
        try{
            File info = new File("Saved-seats.txt");
            Scanner file_reader = new Scanner(info);
            while(file_reader.hasNextLine()){
                String text = file_reader.nextLine();
                System.out.println(text);
            }
            System.out.println("Loaded Successfully");
            file_reader.close();
        }
        catch(IOException e){
            System.out.println("An error occured in loading the file");
            e.printStackTrace();
        }

    }
    public static void show_tickets_info(){
        double total_price = 0;
        for(Ticket ticket_1:tickets){
            ticket_1.print(); /*Prints the information of all tickets*/
            total_price = total_price+ticket_1.getPrice();/*Gets the ticket price from the array list called tickets & adds to the total price*/
        }
        System.out.println("Total Ticket Price : "+ total_price);/* Prints the total price after ticket info*/
    }

    /*Task 14*/
    private static void sort_tickets(){
        ArrayList<Ticket> sorted_list = tickets;

        for(int i =0;i<sorted_list.size();i++){
            for(int x=i+1; x<sorted_list.size();x++){
                if(sorted_list.get(i).getPrice() > sorted_list.get(x).getPrice()){
                    Ticket sorted_seats = sorted_list.get(i);
                    sorted_list.set(i,sorted_list.get(x));
                    sorted_list.set(x,sorted_seats);
                }

            }
        }
        for(Ticket ticket : sorted_list){
            ticket.print();
        }

    }
}

/* Reference*/
/*  https://www.w3schools.com/java/java_classes.asp */
/* https://www.w3schools.com/java/java_constructors.asp */
/* https://www.javatpoint.com/array-in-java */
/*Also refered to lecture slides*/


