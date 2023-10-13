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

        Customer customer = customerInterface.getCustomer(request.queryParams("name"));

        String id = request.queryParams("id");

        List<Reservations> list = new ArrayList();

        list.add(libraryInterface.makeBookReservation(customer, Integer.valueOf(id)));

        if (!list.contains(libraryInterface.libraryInventory[Integer.valueOf(id)])) {

            return "This book does not need to be reserved and can be borrowed right away";
        }

        return libraryInterface.libraryInventory[Integer.valueOf(id) - 1].getInfo() + " by " + libraryInterface.libraryInventory[Integer.valueOf(id) - 1].getAuthor() + " has been reserved";

    }
}
