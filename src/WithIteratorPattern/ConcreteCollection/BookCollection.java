package WithIteratorPattern.ConcreteCollection;

import WithIteratorPattern.Book;
import WithIteratorPattern.ConcreteIterator.BookIterator;
import WithIteratorPattern.Interfaces.Collection;
import WithIteratorPattern.Interfaces.Iterator;

// Implement the Collection interface and provide an iterator for the BookCollection.

public class BookCollection implements Collection<Book> {
    private Book[] books;
    private int index;

    public BookCollection(int size){
        books = new Book[size];
        index = 0;
    }

    public void addBook(Book book){
        if(index<books.length){
            books[index] = book;
            index+=1;
        }
        else{
            System.out.println("Collection is full!");
        }
    }

    @Override
    public Iterator<Book> createIterator() {
        return new BookIterator(books);
    }
}
