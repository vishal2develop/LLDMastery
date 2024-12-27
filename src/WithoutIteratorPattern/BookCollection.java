package WithoutIteratorPattern;

public class BookCollection {
    private Book[] books;
    private int index;

    public BookCollection(int size) {
        books = new Book[size];
        index = 0;
    }

    public void addBook(Book book) {
        if (index < books.length) {
            books[index++] = book;
        } else {
            System.out.println("Collection is full!");
        }
    }

    public Book[] getBooks() {
        return books;
    }
}
