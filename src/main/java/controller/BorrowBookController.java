package controller;

import html.BooksPageRenderer;
import library.*;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        List<Book> customerOrders = Arrays.asList(customer.CustomerInventory.toArray());

        String id = request.queryParams("id");

        for (int i = 0; i < customerOrders.size(); i++) {
            if (customerOrders.get(i) != null) {
                if (customerOrders.get(i).getId() == Integer.valueOf(id)) {
                    return booksPageRenderer.renderMessage("You have already borrowed this book");
                }
            }
        }

        boolean isInUse = libraryInterface.libraryInventory[Integer.valueOf(id) - 1].getIsUse();

        if (isInUse) {
            return booksPageRenderer.renderMessage("this book is already being borrowed");
        }

        List<Integer> IDs = new ArrayList<>();

        Customer customerBooks = libraryInterface.borrowBook(customer, Integer.valueOf(id), 1);

        if (customer.borrowLimit) {
            return booksPageRenderer.renderMessage("You have reached the limit of the amount of book you can borrow at once");
        }

        for (int i = 0; i < customer.CustomerInventory.size(); i++) {
            IDs.add(customer.CustomerInventory.get(i).getId());
        }

        reservations.reservations.add(libraryInterface.libraryInventory[Integer.valueOf(id) - 1]);

        response.cookie("id",request.queryParams("id"));

        return booksPageRenderer.renderList(customerBooks.CustomerInventory, IDs);
    }
}
