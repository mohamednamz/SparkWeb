package controller;

import html.BooksPageRenderer;
import library.Book;
import library.LibraryInterface;
import spark.Request;
import spark.Response;
import spark.Route;

public class LibraryController implements Route {

    private LibraryInterface libraryInterface;
    private BooksPageRenderer booksPageRenderer;

    public LibraryController(LibraryInterface libraryInterface,
                             BooksPageRenderer booksPageRenderer) {
        this.libraryInterface = libraryInterface;
        this.booksPageRenderer = booksPageRenderer;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        Book[] books = libraryInterface.availableBooks();
        request.queryParams("id");
        return booksPageRenderer.render(books);
    }



}
