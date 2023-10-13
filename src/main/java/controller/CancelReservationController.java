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

    private Reservations reservations;


    public CancelReservationController(LibraryInterface libraryInterface,
                                       CustomerInterface customerInterface) {
        this.customerInterface = customerInterface;
        this.libraryInterface = libraryInterface;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        Customer customer = customerInterface.getCustomer(request.queryParams("name"));

        int id = Integer.valueOf(request.queryParams("id"));
        int date = Integer.valueOf(request.queryParams("date"));

        libraryInterface.borrowBook(customer,id,date);

        List<Reservations> list = new ArrayList();

        list.add(libraryInterface.makeBookReservation(customer, id));

        libraryInterface.cancelReservation(customer,id,date);

        return "your reservation for: " + libraryInterface.libraryInventory[id - 1].getInfo() + " by " + libraryInterface.libraryInventory[id - 1].getAuthor() + " has been cancelled";
    }
}
