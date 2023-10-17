import controller.*;
import html.BooksPageRenderer;
import library.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import static spark.Spark.*;

public class Main {

    public static void setUpServer() {
        staticFiles.location("/public");
        port(80);

        //ArrayList<Integer> list = new ArrayList<>();
        //LinkedList<Integer>
        //Queue<Integer>

    }

    public static void main(String[] args) {

        setUpServer();

        LibraryInterface libraryInterface = new LibraryInterface();
        libraryInterface.initialiseLibraryCatalogue();

        BooksPageRenderer booksPageRenderer = new BooksPageRenderer();
        LibraryController libraryController = new LibraryController(libraryInterface, booksPageRenderer);

        CustomerInterface customerInterface = new CustomerInterface();

        AddCustomerController addCustomerController = new AddCustomerController(customerInterface);

        ArrayOfRoutes arrayOfRoutes = new ArrayOfRoutes();

        Customer customer = new Customer();
        customer.Name = "Namz";
        customer.userId = 5;
        customer.customerOrderHistory = new List();

        Customer customer2 = new Customer();
        customer2.Name = "Umar";
        customer2.userId = 4;
        customer2.customerOrderHistory = new List();

        Reservations reservations = new Reservations();



        customerInterface.addCustomer("Namz");
        customerInterface.addCustomer("Umar");

        BookReservationController bookReservationController = new BookReservationController(libraryInterface,customerInterface,booksPageRenderer,customer, reservations);

        BorrowBookController borrowBookController = new BorrowBookController(libraryInterface,customerInterface,customer,booksPageRenderer,libraryController, reservations);

        CustomerBorrowedBooksController customerBorrowedBooksController = new CustomerBorrowedBooksController(libraryInterface,customerInterface,booksPageRenderer);

        CancelReservationController cancelReservationController = new CancelReservationController(libraryInterface,customerInterface);

        CustomerOrderHistoryController customerOrderHistoryController = new CustomerOrderHistoryController(libraryInterface, customerInterface,booksPageRenderer, customer);

        ReturnBookController returnBookController = new ReturnBookController(libraryInterface,customerInterface);

        PayOutstandingFinesController payOutstandingFinesController = new PayOutstandingFinesController(libraryInterface,customerInterface);

        HomePageController homePageController = new HomePageController(libraryController,booksPageRenderer,customerInterface, arrayOfRoutes);

        YourReservationsController yourReservationsController = new YourReservationsController(libraryInterface,customerInterface,reservations,booksPageRenderer);

        //get("/books", libraryController);

        //get("/books", addCustomerController);

        //get("/books", customerOrderHistoryController);

        get("/books/myReservations",yourReservationsController);

        get("/books/borrow",borrowBookController);

        get("/books/reserve", bookReservationController);

        get("/books/customer", customerBorrowedBooksController);

        get("/books/cancel", cancelReservationController);

        get("/books/return",returnBookController);

        get("/books/fines",payOutstandingFinesController);

        get("/books/library", libraryController);

        get("/customers", new CustomerController(customerInterface));

        get("/login", new LoginController(libraryController, homePageController));

        get("/books/homepage",new HomePageController(libraryController,booksPageRenderer,customerInterface, arrayOfRoutes));

        get("/books/borrowed",customerBorrowedBooksController);

        get("/books/availableReservations", new GetReservationsController(libraryInterface, reservations,customerInterface,booksPageRenderer));

        get("/returnToHomepage", new ReturnToHomepageController(libraryInterface,customerInterface,booksPageRenderer,arrayOfRoutes));


    }
}