package controller;

import html.BooksPageRenderer;
import library.*;
import spark.Request;
import spark.Response;
import spark.Route;

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

        return booksPageRenderer.renderList(customerReservations);
    }
}
