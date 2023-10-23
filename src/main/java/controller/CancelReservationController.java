package controller;

import library.Customer;
import library.CustomerInterface;
import library.LibraryInterface;
import library.Reservations;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class CancelReservationController implements Route {

    private LibraryInterface libraryInterface;
    private CustomerInterface customerInterface;

    public CancelReservationController(LibraryInterface libraryInterface,
                                       CustomerInterface customerInterface) {
        this.customerInterface = customerInterface;
        this.libraryInterface = libraryInterface;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        String customerName = request.cookie("name");

        String bookId = request.queryParams("id");

        Customer customer = customerInterface.getCustomer(customerName);

        int id = Integer.valueOf(bookId);
        int date = 1;

        libraryInterface.cancelReservation(customer,id,date);

        return "your reservation for: " + libraryInterface.libraryInventory[id - 1].getInfo() + " by " + libraryInterface.libraryInventory[id - 1].getAuthor() + " has been cancelled";
    }

}
