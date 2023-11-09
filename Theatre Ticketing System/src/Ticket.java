/*Task 10*/
public class Ticket {

     int row;// Declaring the instance variable
     int seat;//Declaring the instance variable
     int price;// Declaring the instance variable
    Person human;


    public Ticket(int row , int seat,int price,Person human){
        this.row=row;
        this.seat=seat;
        this.price=price;
        this.human=human;


    }
    /*Task 11*/
    /* Prints all the information from a ticket*/
    public void print(){
        System.out.println(this.row);
        System.out.println(this.seat);
        System.out.println(this.price);
        System.out.println(this.human.firstname);
        System.out.println(this.human.Surname);
        System.out.println(this.human.Email);
    }
    public int getRow() {
        return row;
    }
    public int getSeat() {

        return seat;
    }
    public int getPrice() {
        return price;
    }

}
