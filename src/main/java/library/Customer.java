package library;


import java.util.ArrayList;

public class Customer {
    public String Name;
    public List CustomerInventory = new List();
    public List customerOrderHistory = new List();
    public List CustomerWishList = new List();
    public int outstandingFines;
    public boolean Banned;
    public int userId;

    public boolean borrowLimit;

    public List customerReservations = new List();

    public void fine(int fine) {

        outstandingFines = outstandingFines + fine;

        System.out.println("Your outstanding fines amount to: £" + outstandingFines);
    }




}
