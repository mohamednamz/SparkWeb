package library;

public class Reservations {
   public List reservations = new List();

   public Book[] getList() {
       return reservations.books;
   }

    Queue<Book> queue = new Queue<>();

    Queue<Integer> customerId = new Queue<>();

    Queue<Integer> bookId = new Queue<>();
}
