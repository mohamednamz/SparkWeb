package controller;

import library.Customer;
import library.CustomerInterface;
import library.LibraryInterface;
import spark.Request;
import spark.Response;
import spark.Route;

public class PayOutstandingFinesController implements Route {

    public LibraryInterface libraryInterface;

    public CustomerInterface customerInterface;

    public PayOutstandingFinesController(LibraryInterface libraryInterface, CustomerInterface customerInterface) {
        this.libraryInterface = libraryInterface;
        this.customerInterface = customerInterface;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        Customer customer = customerInterface.getCustomer(request.queryParams("name"));

        int bookId = Integer.valueOf(request.queryParams("bookId"));

        int fine = Integer.valueOf(request.queryParams("payment"));

        libraryInterface.borrowBook(customer,bookId,1);

        libraryInterface.returnBook(customer,25,bookId);

        libraryInterface.payOffOutstandingFines(customer,bookId,fine);

        return "Outstanding fines have been paid";
    }
}
