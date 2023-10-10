import com.sun.jdi.Method;
import controller.AddCustomerController;
import controller.LibraryController;
import html.BooksPageRenderer;
import library.Customer;
import library.LibraryInterface;
import library.List;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import static spark.Spark.*;

public class Main {

    public static void setUpServer() {
        staticFiles.location("/public");
        port(80);
    }

    public static void main(String[] args) {

        setUpServer();

        LibraryInterface libraryInterface = new LibraryInterface();
        libraryInterface.initialiseLibraryCatalogue();

        BooksPageRenderer booksPageRenderer = new BooksPageRenderer();
        LibraryController libraryController = new LibraryController(libraryInterface, booksPageRenderer);

        get("/", libraryController);
    }
}