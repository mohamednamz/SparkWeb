package controller;

import library.CustomerInterface;
import library.LibraryInterface;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddCustomerController implements Route {

    private CustomerInterface customerInterface;

    public AddCustomerController(CustomerInterface customerInterface) {
        this.customerInterface = customerInterface;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String customerName = request.queryParams("name");
        customerInterface.addCustomer(customerName);
        return "Customer has been added";
    }
}
