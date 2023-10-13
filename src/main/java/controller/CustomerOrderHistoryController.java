package controller;

import html.BooksPageRenderer;
import library.Book;
import library.Customer;
import library.CustomerInterface;
import library.LibraryInterface;
import spark.Request;
import spark.Response;
import spark.Route;

public class CustomerOrderHistoryController implements Route {

    private LibraryInterface libraryInterface;

    private BooksPageRenderer renderer;

    private CustomerInterface customerInterface;

    private Customer customer;

    public CustomerOrderHistoryController(LibraryInterface libraryInterface, CustomerInterface customerInterface, BooksPageRenderer renderer,Customer customer) {
        this.libraryInterface = libraryInterface;
        this.customerInterface = customerInterface;
        this.renderer = renderer;
        this.customer = customer;

    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        Customer customer = customerInterface.getCustomer(request.queryParams("name"));

        Book[] books = libraryInterface.myOrderHistory(customer);

        return renderer.render(books);
    }
}
