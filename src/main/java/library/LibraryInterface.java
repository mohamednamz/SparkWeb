package library;

import java.util.ArrayList;

public class LibraryInterface {

    Reservations reservations = new Reservations();

    BorrowedBook borrowedBook = new BorrowedBook();



    public Book[] libraryInventory = new Book[]{
            new Book("House Without Windows", "Nadia Hashimi", "Fiction", "Hackney", 1, 2),
            new Book("A Thousand Splendid Suns", "Khaled Hosseini", "Fiction", "Bethnal Green", 2, 3),
            new Book("Surrounded by idiots", "Thomas Erikson", "Non-fiction", "Strand", 3, 4),
            new Book("Why Nations Fails: The Origins of Power", "James Robinson", "Business", "Waterloo", 4, 1),
            new Book("The Silk Roads: A New History", "Peter Frankopan", "History", "Brixton", 5, 2),
            new Book("The Power of Letting Go", "Katy O'shaun", "Self-help", "Lewisham", 6, 3),
            new Book("The Beekeeper of Aleppo", "Christy Lefteri", "Fiction", "Enfield", 7, 1),
            new Book("Angels and Demons", "Jack Pearcy", "Fiction", "Hackney", 8, 6),
            new Book("Harry Potter and the Philsophers Stone", "J.K Rowling", "Fiction", "Strand", 9, 1),
            new Book("Harry Potter and the Goblet of Fire", "J.K Rowling", "Fiction", "Brixton", 10, 2)
    };

    public Book[] initialiseLibraryCatalogue() {
        //TODO come back to make it check whether there is the same book for the quantity counter.
        for (int i = 0; i < libraryInventory.length; i++) {
            libraryInventory[i].bookId = i + 1;
        }
        return libraryInventory;
    }

    public Book[] LibraryCatalogue() {

        for (int i = 0; i < libraryInventory.length; i++) {
            libraryInventory[i].print();
        }
        return libraryInventory;
    }

    public Book[] availableBooks() {

        for (int i = 0; i < libraryInventory.length; i++) {
            if (!libraryInventory[i].inUse) {
                libraryInventory[i].print();
            }
        }
        return libraryInventory;
    }

    public Book[] myOrderHistory(Customer customer) {

        for (int i = 0; i < customer.customerOrderHistory.books.length; i++) {
            customer.customerOrderHistory.books[i].print();
        }
        return customer.customerOrderHistory.books;
    }

    public Book[] myWishList(Customer customer, int bookIdentificationNumber) {

        for (int i = 0; i < libraryInventory.length; i++) {

            if (bookIdentificationNumber == libraryInventory[i].bookId) {
                customer.CustomerWishList.add(libraryInventory[i]);
            }
        }
        return customer.CustomerWishList.books;
    }

    public Customer borrowBook(Customer customer, int bookIdentificationNumber, int todaysDate) {

        if (customer.CustomerInventory.index != 9) {

            for (int i = 0; i < libraryInventory.length; i++) {
                if (customer.Banned) {
                    System.out.println("You have been banned from borrowing additional books as you have overdue book/s to return");
                    return customer;
                }
                // TODO unsure
                if (libraryInventory[i].reserved) {
                    if (libraryInventory[i].borrowerId != customer.userId) {
                        System.out.println("This book has already been reserved");
                        return customer;
                    } else {
                        reservations.reservations.FastRemove(getIndex(bookIdentificationNumber));
                    }
                }
                if (bookIdentificationNumber == libraryInventory[i].bookId && !libraryInventory[i].inUse
                        && libraryInventory[i].quantity > 0) {
                    libraryInventory[i].inUse = true;
                    libraryInventory[i].quantity--;
                    libraryInventory[i].dateBorrowed = todaysDate;
                    libraryInventory[i].printReceipt();
                    libraryInventory[i].borrowerId = customer.userId;
                    customer.CustomerInventory.add(libraryInventory[i]);
                    customer.customerOrderHistory.add(libraryInventory[i]);
                    if (libraryInventory[i].reserved) {
                        if (reservations.reservations.books[0].listOfIds == null) {
                            libraryInventory[i].reserved = false;
                        }
                    }
                    return customer;
                } else {
                    if (libraryInventory[i].inUse && libraryInventory[i].bookId == bookIdentificationNumber) {
                        System.out.println("This book is currently not in the library");
                    }
                }
            }
        } else {
            customer.borrowLimit = true;
            System.out.println("You have reached the limit of the amount of book you can borrow at once");
        }
        return customer;
    }

    public Reservations makeBookReservation(Customer customer, int bookIdentificationNumber) {

        for (int i = 0; i < libraryInventory.length; i++) {

            if (libraryInventory[i].bookId == bookIdentificationNumber && libraryInventory[i].inUse) {
                reservations.reservations.add(libraryInventory[i]);
                libraryInventory[i].reserved = true;

                for (int j = 0; j < reservations.reservations.books.length; j++) {
                    if (reservations.reservations.get(j).bookId == bookIdentificationNumber) {
                        reservations.reservations.books[j].listOfIds.add(bookIdentificationNumber);
//TODO fix listofids list, only takes reserve ids not id currently in use... i think this is fixed.
                        reservations.reservations.books[j].reservedId = customer.userId;
                        //reservations.reservations.books[j].listOfIds.add(customer.userId);
                        System.out.println(libraryInventory[i].title + " by " + libraryInventory[i].author + " has been reserved");
                        return reservations;
                    }
                }
            }
        }
        return reservations;
    }

    public java.util.List<Book> getReservations(Customer customer) {

        java.util.List<Book> reservation = new ArrayList<>();


        for (int i = 0; i < reservations.reservations.books.length; i++) {
            if (reservations.reservations.get(i) != null) {
                if (customer.userId == reservations.reservations.get(i).reservedId) {
                    reservation.add(reservations.reservations.get(i));

                }
            }
        }
        return reservation;
    }

    public Reservations makeBookReservationQueue(Customer customer, int bookIdentificationNumber) {

        for (int i = 0; i < libraryInventory.length; i++) {
            if (libraryInventory[i].bookId == bookIdentificationNumber && libraryInventory[i].inUse) {
                reservations.queue.insertInQueue(libraryInventory[i]);
                libraryInventory[i].reserved = true;
            }

            for (int j = 0; j < reservations.queue.size; j++) {
                if (reservations.queue.checkQueue(j).bookId == bookIdentificationNumber) {
                    reservations.customerId.insertInQueue(customer.userId);
                    reservations.bookId.insertInQueue(bookIdentificationNumber);
                    System.out.println(libraryInventory[i].title + " by " + libraryInventory[i].author + " has been reserved");
                    return reservations;
                }
            }
        }
        return reservations;
    }


    public Book[] customerBorrowedBooks(Customer customer) {

        for (int i = 0; i < customer.CustomerInventory.books.length; i++) {
            customer.CustomerInventory.books[i].print();
        }
        return customer.CustomerInventory.books;
    }

    public Reservations cancelReservationQueue(Customer customer, int bookIdentificationNumber, int cancellationDate) {

        boolean stillReserved = false;

        for (int i = 0; i < reservations.queue.size; i++) {
            if (reservations.bookId.checkQueue(i) != null) {
                if (customer.userId == reservations.customerId.checkQueue(i) &&
                        bookIdentificationNumber == reservations.bookId.checkQueue(i)) {
                    reservations.bookId.removeFromQueue(reservations.bookId.checkQueue(i));
                    reservations.customerId.removeFromQueue(reservations.customerId.checkQueue(i));

                    if (reservations.bookId.checkQueue(i) != null) {
                        if (reservations.bookId.checkQueue(i) == bookIdentificationNumber) {
                            stillReserved = true;
                        }
                    }
                    if (!stillReserved) {
                        libraryInventory[i].reserved = false;
                        reservations.queue.removeFromQueue(libraryInventory[i]);
                        return reservations;
                    }

                }
            }
        }
        return reservations;
    }

    public Reservations cancelReservation(Customer customer, int bookIdentificationNumber, int cancellationDate) {

        for (int i = 0; i < reservations.reservations.books.length; i++) {
            if (reservations.reservations.books[i] != null) {
                if (customer.userId == reservations.reservations.books[i].reservedId &&
                        bookIdentificationNumber == reservations.reservations.books[i].bookId) {

                    //reservations.reservations.books[i].listOfIds.remove(i);


                    //TODO if there is a que i need to remove the reservation and replace with next person in list.
                    for (int j = 0; j < libraryInventory.length; j++) {
                        if (libraryInventory[j].bookId == bookIdentificationNumber) {
                            libraryInventory[j].reserved = false;
                            //reservations.reservations.books[i].listOfIds.remove(i);
                            reservations.reservations.books[i].reservedId = 0;
                            reservations.reservations.remove(i);
                            return reservations;
                        }
                    }
                    return reservations;
                }
            }
        }
        return reservations;
    }

    public List returnBook(Customer customer, int returnDate, int bookIdentificationNumber, CustomerInterface customerInterface) {

        for (int i = 0; i < customer.CustomerInventory.books.length; i++) {
            if (bookIdentificationNumber == customer.CustomerInventory.books[i].bookId) {
                if (returnDate - customer.CustomerInventory.books[i].dateBorrowed > 14) {
                    //customer.CustomerInventory.books[i].dateReturned = returnDate;
                    customer.fine(returnDate - customer.CustomerInventory.books[i].dateBorrowed);

                } //else {
                customer.CustomerInventory.remove(i);
                customer.borrowLimit = false;

                for (int j = 0; j < libraryInventory.length; j++) {
                    if (bookIdentificationNumber == libraryInventory[j].bookId) {
                        libraryInventory[j].inUse = false;
                        libraryInventory[j].quantity++;

                        if (libraryInventory[j].reservedId != 0) {
                            for (int x = 0; x < customerInterface.customerList.size(); x++) {
                                if (customerInterface.doesIdMatch(libraryInventory[j].reservedId)) {
                                    libraryInventory[j].reserved = false;
                                    borrowBook(customerInterface.getCustomer(libraryInventory[j].reservedId), libraryInventory[j].bookId, 1);
                                    cancelReservation(customerInterface.getCustomer(libraryInventory[j].reservedId), libraryInventory[j].bookId, 1);
                                    reservations.reservations.add(libraryInventory[j]);
                                }
                            }
                        }
                    }
                }

                return customer.CustomerInventory;
            }

        }
        return customer.CustomerInventory;
    }

    public Customer payOffOutstandingFines(Customer customer, int customerPayment) {

        int finePayment = customer.outstandingFines - customerPayment;

        if (finePayment == 0) {
            customer.outstandingFines = 0;
            //libraryInventory[index].quantity++;
            System.out.println("you have paid off all your fines");
        } else {
            customer.outstandingFines = finePayment;
            System.out.println("you have an outstanding fine balance of: £" + customer.outstandingFines);
        }
        return customer;
    }

    public Customer banUser(Customer customer, int bookIdentificationNumber, int todaysDate) {

        for (int i = 0; i < customer.CustomerInventory.books.length; i++) {
            if (todaysDate - customer.CustomerInventory.books[i].dateBorrowed > 25) {
                customer.Banned = true;
            }
        }
        return customer;
    }

    public int getIndex(int bookIdentificationNumber) {

        int index = 0;

        for (int i = 0; i < reservations.reservations.books.length; i++) {
            if (bookIdentificationNumber == reservations.reservations.books[i].bookId) {
                index = i;
            }
        }
        return index;
    }
}
