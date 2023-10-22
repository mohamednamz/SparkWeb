package controller;

import html.BooksPageRenderer;
import library.Customer;
import library.CustomerInterface;
import library.LibraryInterface;
import spark.Request;
import spark.Response;
import spark.Route;

public class ReturnToHomepageController implements Route {

    LibraryInterface libraryInterface;

    CustomerInterface customerInterface;

    BooksPageRenderer booksPageRenderer;

    ArrayOfRoutes arrayOfRoutes;

    public ReturnToHomepageController(LibraryInterface libraryInterface, CustomerInterface customerInterface, BooksPageRenderer booksPageRenderer, ArrayOfRoutes arrayOfRoutes) {
        this.libraryInterface = libraryInterface;
        this.customerInterface = customerInterface;
        this.booksPageRenderer = booksPageRenderer;
        this.arrayOfRoutes = arrayOfRoutes;

    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

         String customerName = request.cookie("name");

        Customer customer = customerInterface.getCustomer(customerName);

        return booksPageRenderer.render(customer.Name, arrayOfRoutes);

    }
}
