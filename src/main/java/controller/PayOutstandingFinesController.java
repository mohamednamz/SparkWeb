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

        String customerName = request.cookie("name");

        String bookId = request.queryParams("id");

        Customer customer = customerInterface.getCustomer(customerName);

        int fine = customer.outstandingFines;

        customer = libraryInterface.payOffOutstandingFines(customer,fine);

        if (customer.outstandingFines == 0) {
            return "Outstanding fines have been paid";
        }

        return "You owe a total of £: " + customer.outstandingFines;
    }
}
