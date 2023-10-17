package controller;

public class ArrayOfRoutes {

    String[] routes = {"/library","/cancel","/return","/fines","/login", "/borrowed", "/availableReservations", "/myReservations"};

    String [] routeNames = {"book selection", "cancel a reservation", "return a book", "pay off a fine", "back to login page", "your books", "reservations list", "your reservations"};


    public String getRoutes(int index) {
        return routes[index];
    }

    public int length() {
        return routes.length;
    }

    public String getRouteNames(int index) {
        return routeNames[index];
    }

    public int routeNamesLength() {
        return routeNames.length;
    }

    //removed customer and customers... do they fit into this? i think not.

}

