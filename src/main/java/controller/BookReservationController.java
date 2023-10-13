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

    private Customer customer;


    public BookReservationController(LibraryInterface libraryInterface, CustomerInterface customerInterface, BooksPageRenderer booksPageRenderer, Customer customer) {
        this.libraryInterface = libraryInterface;
        this.customerInterface = customerInterface;
        this.booksPageRenderer = booksPageRenderer;
        this.customer = customer;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {

        Customer customer = customerInterface.getCustomer(request.cookie("name"));
        String id = request.queryParams("id");

        if (customer == null) {
            throw new Exception("Customer id not found");
        }

        if (id == null) {
            throw new Exception("Book id not found");
        }

        List<Reservations> reservations = new ArrayList();

        reservations.add(libraryInterface.makeBookReservation(customer, Integer.valueOf(id)));

        if (!reservations.contains(libraryInterface.libraryInventory[Integer.valueOf(id)])) {

            return "This book does not need to be reserved and can be borrowed right away";
        }

        return libraryInterface.libraryInventory[Integer.valueOf(id) - 1].getInfo() + " by " + libraryInterface.libraryInventory[Integer.valueOf(id) - 1].getAuthor() + " has been reserved";

    }
}
