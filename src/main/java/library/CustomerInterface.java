package library;

import java.util.ArrayList;
import java.util.List;

public class CustomerInterface {

    public List<Customer> customerList = new ArrayList<>();

    public void addCustomer(String customerName) {
        Customer customer = new Customer();
        customer.Name = customerName;
        customerList.add(customer);
        customer.userId = customerList.size();
    }

    public Customer getCustomer(String customerName) {
        for (Customer customer: customerList) {
            if(customer.Name.equals(customerName)) {
                return customer;
            }
        }
        return null;
    }

    public boolean doesIdMatch(int customerId) {
        for (Customer customer: customerList) {
            if(customer.userId == customerId) {
                return true;
            }
        }
        return false;
    }

    public Customer getCustomer(int customerId) {
        for (Customer customer: customerList) {
            if(customer.userId == customerId) {
                return customer;
            }
        }
        return null;
    }


}
