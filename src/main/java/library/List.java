package library;

public class List {
    Book[] books = new Book[1];
    int index = 0;


    public void add(Book element) {
        if (index >= books.length) {
            grow();
        }
        books[index] = element;
        index++;
    }

    private void grow() {
        Book[] newArr = new Book[books.length * 2];

        for (int i = 0; i < books.length; i++) {
            newArr[i] = books[i];
        }
        books = newArr;
    }

    public void FastRemove(int index) {
        books[index] = null;

        for (int i = 0; i < books.length - 1; i++) {
            if (books[i] == null) {
                books[i] = books[i + 1];
                books[i + 1] = null;
            }
        }
    }

    public int size() {
        int size = 0;

        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) {
                size++;
            }
        }
        return size;
    }

    public boolean isEmpty() {

        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) {
                return false;
            }
        }
        return true;
    }

    public Book[] toArray() {
        Book[] newArray = new Book[books.length];

        for (int i = 0; i < books.length; i++) {
            newArray[i] = books[i];
        }
        return newArray;
    }

    public void appendElementToList(Book element) {

        if (index >= books.length) {
            append();
        }
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = element;
                print();
                return;
            }
        }
    }

    public void append() {
        Book[] newArr = new Book[books.length + 1];

        for (int i = 0; i < books.length; i++) {
            newArr[i] = books[i];
        }
        books = newArr;
    }

    public boolean remove(int index) {
        if (index > books.length) {
            return false;
        }
        books[index] = null;
        shiftList(index);
        print();
        return true;
    }

    private void shiftList(int index) {
        Book[] newArray = new Book[books.length - 1];

        for (int i = 0; i < newArray.length; i++) {
            if (i < index) {
                newArray[i] = books[i];
            }
            if (i == index || i > index) {
                newArray[i] = books[i + 1];
            }
        }
        books = newArray;
    }

    public Book set(int index, Book value) {
        Book[] newArray = new Book[1];
        int x = 0;

        newArray[x] = books[index];
        books[index] = value;
        value = newArray[x];
        print();
        System.out.println("Element that has been replaced: " + value);

        return value;
    }

    public Book get(int index) {
        return books[index];
    }


    public void print() {

        System.out.print("[");
        for (int i = 0; i < books.length; i++) {
            if (i != books.length - 1) {
                System.out.print(books[i] + ",");
            }
            if (i == books.length - 1) {
                System.out.print(books[i]);
            }
        }
        System.out.print("]");
        System.out.println();
    }
}
