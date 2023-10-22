package controller;

import html.BooksPageRenderer;
import library.CustomerInterface;
import library.LibraryInterface;
import spark.Request;
import spark.Response;
import spark.Route;

public class LogOutController implements Route {

    LibraryInterface libraryInterface;

    CustomerInterface customerInterface;

    BooksPageRenderer booksPageRenderer;

    CustomerController customerController;

    public LogOutController(LibraryInterface libraryInterface, CustomerInterface customerInterface, BooksPageRenderer booksPageRenderer, CustomerController customerController) {
        this.customerController = customerController;
        this.booksPageRenderer = booksPageRenderer;
        this.customerInterface = customerInterface;
        this.libraryInterface = libraryInterface;
    }



    @Override
    public Object handle(Request request, Response response) throws Exception {

        response.redirect("/customers");

        return customerController.handle(request,response);
    }
}
