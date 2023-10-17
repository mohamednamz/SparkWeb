package controller;

import html.BooksPageRenderer;
import library.*;
import spark.Request;
import spark.Response;
import spark.Route;

public class BorrowBookController implements Route {

    private LibraryInterface libraryInterface;
    private CustomerInterface customerInterface;

    private BooksPageRenderer booksPageRenderer;

    LibraryController libraryController;

    Reservations reservations;

    private Customer customer;


    public BorrowBookController(LibraryInterface libraryInterface,
                                CustomerInterface customerInterface, Customer customer, BooksPageRenderer renderer, LibraryController libraryController, Reservations reservations) {
        this.libraryInterface = libraryInterface;
        this.customerInterface = customerInterface;
        this.customer = customer;
        this.booksPageRenderer = renderer;
        this.libraryController = libraryController;
        this.reservations = reservations;

    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        // Throw error if not here
        String customerName = request.cookie("name");

        Customer customer = customerInterface.getCustomer(customerName);

        String id = request.queryParams("id");

        Customer customerBooks = libraryInterface.borrowBook(customer, Integer.valueOf(id), 1);

        reservations.reservations.add(libraryInterface.libraryInventory[Integer.valueOf(id) - 1]);

        return booksPageRenderer.renderList(customerBooks.customerOrderHistory);
    }
}
