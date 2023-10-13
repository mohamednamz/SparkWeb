package controller;

import html.BooksPageRenderer;
import library.Book;
import library.Customer;
import library.CustomerInterface;
import library.LibraryInterface;
import spark.Request;
import spark.Response;
import spark.Route;

public class BorrowBookController implements Route {

    private LibraryInterface libraryInterface;
    private CustomerInterface customerInterface;

    private BooksPageRenderer booksPageRenderer;

    private Customer customer;


    public BorrowBookController(LibraryInterface libraryInterface,
                                CustomerInterface customerInterface, Customer customer, BooksPageRenderer renderer) {
        this.libraryInterface = libraryInterface;
        this.customerInterface = customerInterface;
        this.customer = customer;
        this.booksPageRenderer = renderer;

    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        // Throw error if not here
        String customerName = request.cookie("name");

        Customer customer = customerInterface.getCustomer(customerName);

        String id = request.queryParams("id");

        Customer customerBooks = libraryInterface.borrowBook(customer, Integer.valueOf(id), 1);

        return booksPageRenderer.renderList(customerBooks.customerOrderHistory);
    }
}
