package controller;

import library.Customer;
import library.CustomerInterface;
import library.LibraryInterface;
import library.Reservations;
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

        String bookId = request.queryParams("id");

        Customer customer = customerInterface.getCustomer(customerName);

        int date = 1;

        int id = Integer.valueOf(bookId);

        libraryInterface.returnBook(customer,30,id);

        return libraryInterface.libraryInventory[id - 1].getInfo() + " by " + libraryInterface.libraryInventory[id - 1].getAuthor() + " has been returned";
    }
}
