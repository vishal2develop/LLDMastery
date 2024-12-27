package WithoutIteratorPattern;

public class Main {
    public static void main(String[] args) {

        BookCollection collection = new BookCollection(3);
        collection.addBook(new Book("The Great Gatsby"));
        collection.addBook(new Book("1984"));
        collection.addBook(new Book("To Kill a Mockingbird"));

        // Traversal logic tied to array structure
        Book[] books = collection.getBooks();
        for (Book book : books) {
            if (book != null) {
                System.out.println(book.getTitle());
            }
        }
    }
}