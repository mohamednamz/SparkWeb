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
import java.util.Arrays;
import java.util.List;

public class CustomerBorrowedBooksController implements Route {

    private LibraryInterface libraryInterface;

    private CustomerInterface customerInterface;

    private BooksPageRenderer booksPageRenderer;

    public CustomerBorrowedBooksController(LibraryInterface libraryInterface, CustomerInterface customerInterface, BooksPageRenderer booksPageRenderer) {
        this.libraryInterface = libraryInterface;
        this.customerInterface = customerInterface;
        this.booksPageRenderer = booksPageRenderer;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        String customerName = request.cookie("name");

        //String id = request.queryParams("id");

        Customer customer = customerInterface.getCustomer(customerName);

        List<Integer> IDs = new ArrayList<>();

        for (int i = 0; i < customer.CustomerInventory.size(); i++) {
            IDs.add(customer.CustomerInventory.get(i).getId());
        }

        if (IDs.isEmpty()) {
            return booksPageRenderer.renderMessage("You have not borrowed any books");
        }



        return booksPageRenderer.renderList(Arrays.asList(customer.CustomerInventory.toArray()), IDs);
    }
}



















