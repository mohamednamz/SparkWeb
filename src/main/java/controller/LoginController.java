package controller;

import spark.Request;
import spark.Response;
import spark.Route;

public class LoginController implements Route {
    private LibraryController libraryController;

    public LoginController(LibraryController libraryController) {
        this.libraryController = libraryController;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        response.cookie("name", request.queryParams("name"));
        return libraryController.handle(request, response);
    }
}
