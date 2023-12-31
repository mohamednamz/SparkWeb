package controller;

import html.BooksPageRenderer;
import library.*;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class BookReservationController implements Route {

    private LibraryInterface libraryInterface;

    private CustomerInterface customerInterface;

    private BooksPageRenderer booksPageRenderer;


    private Reservations reservations;


    public BookReservationController(LibraryInterface libraryInterface, CustomerInterface customerInterface, BooksPageRenderer booksPageRenderer, Reservations reservations) {
        this.libraryInterface = libraryInterface;
        this.customerInterface = customerInterface;
        this.booksPageRenderer = booksPageRenderer;
        this.reservations = reservations;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {

        Customer customer = customerInterface.getCustomer(request.cookie("name"));

        String id = request.queryParams("id");

        libraryInterface.makeBookReservation(customer, Integer.valueOf(id));

        //for some reason there are duplicated books.

        if (!reservations.reservations.contains(libraryInterface.libraryInventory[Integer.valueOf(id) - 1])) {

            return booksPageRenderer.renderMessage("This book does not need to be reserved and can be borrowed right away");
        }

        //customer.customerReservations.add(libraryInterface.libraryInventory[Integer.valueOf(id) - 1]);

        return libraryInterface.libraryInventory[Integer.valueOf(id) - 1].getInfo() + " by " + libraryInterface.libraryInventory[Integer.valueOf(id) - 1].getAuthor() + " has been reserved";

    }
}
