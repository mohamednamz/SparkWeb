package controller;

import html.BooksPageRenderer;
import library.Book;
import library.Customer;
import library.CustomerInterface;
import library.LibraryInterface;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class YourBooksController implements Route {

    LibraryInterface libraryInterface;

    CustomerInterface customerInterface;

    BooksPageRenderer booksPageRenderer;

    public YourBooksController(LibraryInterface libraryInterface, CustomerInterface customerInterface, BooksPageRenderer booksPageRenderer) {
        this.libraryInterface = libraryInterface;
        this.customerInterface = customerInterface;
        this.booksPageRenderer = booksPageRenderer;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {

        String customerName = request.cookie("name");

        Customer customer = customerInterface.getCustomer(customerName);

        List<Book> customerBooks = new ArrayList<>();

        for (int i = 0; i < customer.customerOrderHistory.size(); i++) {
            customerBooks.add(customer.customerOrderHistory.get(i));
        }

        return null;
    }
}
