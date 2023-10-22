package library;

import java.util.ArrayList;
import java.util.List;

public class Book {
    String title;
    String genre;
    String author;
    boolean reserved;
    String location;
    boolean inUse;
    int bookId;
    int quantity;

    int reservedId;

    //TODO CUT OFF

    int borrowerId;

    //TODO seperate the current userId using the book from the reserved list.
    List<Integer> listOfIds = new ArrayList<>();
    int dateBorrowed;
    int dateReturned;

    public Book(String title, String author, String genre, String location, int bookId, int quantity) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.location = location;
        this.bookId = bookId;
        this.quantity = quantity;
    }
    public void print() {
        System.out.println(title);
        System.out.println(author);
        System.out.println("Book id: " + bookId);
        System.out.println();
    }
    public void printReceipt() {

        System.out.println("                                          -Receipt-");
        System.out.println("Book " + bookId + ": " + title);
        System.out.println("Author: " + author);
        System.out.println("Date Borrowed: " + dateBorrowed + "/04/2023");
        System.out.println("Return book by: " + (dateBorrowed + 14)  + "/04/2023");
        System.out.println();
        System.out.println("books returned after the 'return by' date will receive a fine of £1 for every day after the 'return by' date.");
        System.out.println("Customers that do not return their borrowed by within 28 days will be blocked from borrowing additional books");
    }

    public void printFine() {
    }

    public String getInfo() {
        return "Title of the book :  " + title;
    }

    public String getAuthor() {
        return "by " + author;
    }

    public int getId() {
        return bookId;
    }

    public boolean getIsUse() {
        return inUse;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public int getReservedId() {
        return reservedId;
    }


}
