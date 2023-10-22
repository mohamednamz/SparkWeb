package controller;

public class ArrayOfRoutes {

    String[] routes = {"/library","/logout", "/borrowed", "/availableReservations", "/myReservations", "/yourFines"};

    String [] routeNames = {"book selection", "back to login page", "your books", "reservations list", "your reservations", "your fines"};


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

