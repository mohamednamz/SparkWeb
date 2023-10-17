package controller;

import html.BooksPageRenderer;
import library.Book;
import library.Customer;
import library.CustomerInterface;
import spark.Request;
import spark.Response;
import spark.Route;

public class HomePageController implements Route {

    LibraryController libraryController;
    BooksPageRenderer booksPageRenderer;

    ArrayOfRoutes arrayOfRoutes;

    CustomerInterface customerInterface;

    public HomePageController(LibraryController libraryController, BooksPageRenderer booksPageRenderer, CustomerInterface customerInterface, ArrayOfRoutes arrayOfRoutes) {
        this.libraryController = libraryController;
        this.booksPageRenderer = booksPageRenderer;
        this.customerInterface = customerInterface;
        this.arrayOfRoutes = arrayOfRoutes;
    }




    @Override
    public Object handle(Request request, Response response) throws Exception {

        //response.cookie("name", request.queryParams("name"));

        Customer customer = customerInterface.getCustomer(request.queryParams("name"));

        return booksPageRenderer.render(customer.Name, arrayOfRoutes);
    }
}
