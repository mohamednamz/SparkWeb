package controller;

import library.Customer;
import library.CustomerInterface;
import spark.Request;
import spark.Response;
import spark.Route;

public class CustomerController implements Route {

    private CustomerInterface customerInterface;

    public CustomerController(CustomerInterface customerInterface) {
        this.customerInterface = customerInterface;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String html = "";

        for (Customer customer: customerInterface.customerList) {
            String a = " <a href=\"login?name=";
            a += customer.Name + "\">" + "Login " + customer.Name + "</a>";
            html += a;
        }

        return html;

    }
}
