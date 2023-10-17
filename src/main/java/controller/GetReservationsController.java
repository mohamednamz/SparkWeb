package controller;

import html.BooksPageRenderer;
import library.*;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class GetReservationsController implements Route {

    LibraryInterface libraryInterface;

    BooksPageRenderer booksPageRenderer;

    Reservations reservations;

    CustomerInterface customerInterface;

    public GetReservationsController(LibraryInterface libraryInterface, Reservations reservations, CustomerInterface customerInterface, BooksPageRenderer booksPageRenderer) {
        this.customerInterface = customerInterface;
        this.libraryInterface = libraryInterface;
        this.reservations = reservations;
        this.booksPageRenderer = booksPageRenderer;
    }


    public Object handle(Request request, Response response) throws Exception {

        List<Book> books = new ArrayList<>();
        List<Book> listOfReservations = new ArrayList<>();
        List<Book> canReserve = new ArrayList<>();

        Customer customer = customerInterface.getCustomer(request.cookie("name"));

        for (int i = 0; i < libraryInterface.libraryInventory.length; i++) {

            if (libraryInterface.libraryInventory[i].getIsUse()) {
                books.add(libraryInterface.libraryInventory[i]);
            }
        }

        for (int i = 0; i < books.size(); i++) {
            for (int j = 0; j < libraryInterface.libraryInventory.length; j++) {
                if (books.get(i).getId() == libraryInterface.libraryInventory[j].getId()) {
                    listOfReservations.add(books.get(i));
                }
            }
        }

        for (int i = 0; i < listOfReservations.size(); i++) {
            if (listOfReservations.get(i).getBorrowerId() != customer.userId) {
                canReserve.add(listOfReservations.get(i));
            }
        }

        if (canReserve.isEmpty()) {
            return "You already own or are able to borrow all books in the library";
        }

        //request.queryParams("id");
        return booksPageRenderer.renderReservations(canReserve);

    }
}
