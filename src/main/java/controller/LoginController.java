package controller;

import spark.Request;
import spark.Response;
import spark.Route;

public class LoginController implements Route {
    private LibraryController libraryController;

    private HomePageController homePageController;

    public LoginController(LibraryController libraryController, HomePageController homePageController) {
        this.libraryController = libraryController;
        this.homePageController = homePageController;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        response.cookie("name", request.queryParams("name"));
        return homePageController.handle(request,response);

    }
}
