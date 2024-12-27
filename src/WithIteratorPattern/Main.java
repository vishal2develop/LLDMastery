package WithIteratorPattern;

import WithIteratorPattern.ConcreteCollection.BookCollection;
import WithIteratorPattern.Interfaces.Iterator;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BookCollection bookCollection = new BookCollection(3);
        bookCollection.addBook(new Book("The Great Gatsby"));
        bookCollection.addBook(new Book("1984"));
        bookCollection.addBook(new Book("To Kill a Mockingbird"));

        // using the iterator to traverse the collection
        Iterator<Book> iterator = bookCollection.createIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().getTitle());
        }
    }
}
