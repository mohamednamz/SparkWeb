package controller;

import html.BooksPageRenderer;
import library.Customer;
import library.CustomerInterface;
import library.LibraryInterface;
import spark.Request;
import spark.Response;
import spark.Route;

public class YourFinesController implements Route {

    LibraryInterface libraryInterface;

    CustomerInterface customerInterface;

    BooksPageRenderer booksPageRenderer;

    public YourFinesController(LibraryInterface libraryInterface, CustomerInterface customerInterface, BooksPageRenderer booksPageRenderer) {
        this.libraryInterface = libraryInterface;
        this.customerInterface = customerInterface;
        this.booksPageRenderer = booksPageRenderer;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        String customerName = request.cookie("name");

        Customer customer = customerInterface.getCustomer(customerName);

        if (customer.outstandingFines == 0) {
            return "You have no outstanding fines";
        }

        return booksPageRenderer.renderFine(customer.outstandingFines);
    }
}
