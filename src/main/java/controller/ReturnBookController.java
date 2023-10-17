package controller;

import library.Customer;
import library.CustomerInterface;
import library.LibraryInterface;
import spark.Request;
import spark.Response;
import spark.Route;

public class ReturnBookController implements Route {

    LibraryInterface libraryInterface;
    CustomerInterface customerInterface;

    public ReturnBookController(LibraryInterface libraryInterface, CustomerInterface customerInterface) {
        this.libraryInterface = libraryInterface;
        this.customerInterface = customerInterface;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {

        String customerName = request.cookie("name");

        Customer customer = customerInterface.getCustomer(customerName);

        int date = 1;

        int bookId = Integer.valueOf(request.queryParams("bookId"));

        libraryInterface.borrowBook(customer,bookId,date);

        libraryInterface.returnBook(customer,date,bookId);

        return libraryInterface.libraryInventory[bookId - 1].getInfo() + " by " + libraryInterface.libraryInventory[bookId - 1].getAuthor() + " has been returned";
    }
}
