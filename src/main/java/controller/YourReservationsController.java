package controller;

import html.BooksPageRenderer;
import library.*;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class YourReservationsController implements Route {

    LibraryInterface libraryInterface;

    BooksPageRenderer booksPageRenderer;

    Reservations reservations;
    CustomerInterface customerInterface;

    public YourReservationsController(LibraryInterface libraryInterface, CustomerInterface customerInterface, Reservations reservations, BooksPageRenderer booksPageRenderer) {
        this.libraryInterface = libraryInterface;
        this.customerInterface = customerInterface;
        this.reservations = reservations;
        this.booksPageRenderer = booksPageRenderer;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {

        String customerName = request.cookie("name");

        Customer customer = customerInterface.getCustomer(customerName);

        List<Book> customerReservations = libraryInterface.getReservations(customer);

        List<Integer> IDs = new ArrayList<>();

        for (int i = 0; i < libraryInterface.getReservations(customer).size(); i++) {
            IDs.add(libraryInterface.getReservations(customer).get(i).getId());
        }

        if (IDs.isEmpty()) {
            return booksPageRenderer.renderMessage("You have no books reserved");
        }

        //return booksPageRenderer.renderList(customerReservations, IDs);
        return booksPageRenderer.renderYourListOfReservations(customerReservations, IDs);
    }
}
